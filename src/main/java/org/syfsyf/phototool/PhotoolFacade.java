package org.syfsyf.phototool;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.picocontainer.annotations.Inject;
import org.slf4j.LoggerFactory;
import org.syfsyf.phototool.cfg.Config;
import org.syfsyf.phototool.cfg.ConfigService;
import org.syfsyf.phototool.cfg.Profile;
import org.syfsyf.phototool.gui.JobsStatusDto;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The Class Service.
 */
public class PhotoolFacade {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PhotoolFacade.class);

    @Inject
    private ConfigService configService;

    /**
     * Creates the data model.
     *
     * @return the data model
     * @throws FileNotFoundException the file not found exception
     * @deprecated
     */
    @Deprecated
    public DataModel createDataModel() throws FileNotFoundException {
        LOGGER.info("creating data model");
        DataModel dataModel = new DataModel();

        dataModel.setCwd(new File(System.getProperty("user.dir")));
        dataModel.setProfile(configService.loadProfile());
        dataModel.setConfig(configService.loadConfig());
        dataModel.setFiles(scanFiles(dataModel.getCwd()));

        LOGGER.info("data model created:" + dataModel);

        return dataModel;
    }

    public DataModel createDataModel(File imagesDir) throws FileNotFoundException {
        LOGGER.info("creating data model");
        DataModel dataModel = new DataModel();

        dataModel.setCwd(imagesDir);
        dataModel.setProfile(configService.loadProfile());
        dataModel.setConfig(configService.loadConfig());
        dataModel.setFiles(scanFiles(dataModel.getCwd()));

        LOGGER.info("data model created:" + dataModel);

        return dataModel;
    }

    /**
     * Scan files.
     *
     * @param cwd the cwd
     * @return the list
     */
    private List<File> scanFiles(File cwd) {
        List<File> files = new ArrayList<>();

        FilenameFilter filter = new FilenameFilter() {

            @Override
            public boolean accept(File dir, String name) {
                // TODO Auto-generated method stub
                return false;
            }
        };
        cwd.listFiles(filter);

        for (File file : cwd.listFiles()) {
            if (!file.isFile())
                continue;

            String ext = FilenameUtils.getExtension(file.getName().toLowerCase());

            if (!"jpg".equals(ext) && !"png".equals(ext)) {
                continue;
            }
            files.add(file);
        }
        return files;
    }

    /**
     * Compute out dir.
     *
     * @param model the model
     * @return the string
     */
    public String computeOutDir(DataModel model) {
        StringBuilder builder = new StringBuilder();
        builder.append("_out_");

        if (model.getProfile().isResize()) {
            builder.append("r" + model.getProfile().getResizeWidth());
        }
        if (model.getProfile().isBorder() && model.getProfile().getBorderSize() > 0) {
            int w = model.getProfile().getBorderSize();
            builder.append("b" + w);
        }
        if (model.getProfile().isAutolevel()) {
            builder.append("a");
        }
        return model.getCwd().getAbsolutePath() + "\\" + builder.toString();

    }

    /**
     * To hex color.
     *
     * @param color the color
     * @return the string
     */
    public static String toHexColor(Color color) {
        return String.format("\"#%1$02x%2$02x%3$02x\"", color.getRed(), color.getGreen(), color.getBlue());
    }

    String doubleToString(Double d) {

        String res = Double.toString(d);
        return res.replaceAll("\\,", ".");
    }

    /**
     * Creates the jobs.
     *
     * @param dataModel the data model
     */
    public void createJobs(DataModel dataModel) {

        dataModel.setJobs(new ArrayList<Job>());

        String outDir = computeOutDir(dataModel);

        Profile profile = dataModel.getProfile();
        Config config = dataModel.getConfig();
        for (File f : dataModel.getFiles()) {

            IMIdentify identify = identify(f, config.getImgMagicIdentify());
            LOGGER.debug("identify:" + identify);

            List<String> cmds = new ArrayList<String>();
            Job job = new MultiCmdJob(cmds);

            StringBuilder builder;

            if (profile.isGeoTag()) {
                builder = new StringBuilder();
                builder.append(config.getExiftool());
                String lat = doubleToString(profile.getGeoPoint().getLat());
                String lng = doubleToString(profile.getGeoPoint().getLng());
                builder.append(String.format(" -GPSLatitude=%s -GPSLongitude=%s -GPSLatitudeRef=0 -GPSLongitudeRef=0 ", lat, lng));
                builder.append(" \"");
                builder.append(f.getAbsolutePath());
                builder.append("\"");
                cmds.add(builder.toString());

            }

            builder = new StringBuilder();
            builder.append(config.getImgMagicConvert());
            builder.append(" \"");
            builder.append(f.getAbsolutePath());
            builder.append("\"");

            if (profile.isResize()) {
                int w = profile.getResizeWidth();
                if (profile.isBorder()) {
                    w -= profile.getBorderSize() * 2;
                }
                builder.append(" -resize " + w + "x" + w);
            }
            if (profile.isBorder()) {
                int s = profile.getBorderSize();
                builder.append(" -bordercolor " + profile.getBorderColorHex());
                builder.append(" -border " + s + "x" + s);

            }
            if (profile.isAutolevel()) {
                builder.append(" -auto-level");
            }

            if (profile.getCustomParams() != null && !"".equals(profile.getCustomParams())) {
                builder.append(" " + profile.getCustomParams() + " ");
            }

            File outFile = new File(outDir, f.getName());
            builder.append(" \"" + outFile.getAbsolutePath() + "\"");
            LOGGER.info("cmd:" + builder.toString());

            cmds.add(builder.toString());

            if (profile.isAddSignature()) {

                builder = new StringBuilder();
                builder.append(config.getImgMagicComposite());
                builder.append(" -gravity " + profile.getSigGravity() + " -geometry " + profile.getSigGeometry() + " ");

                builder.append("( \"" + profile.getSigFile() + "\" -resize \"" + profile.getSigResize() + "\" ) ");
                builder.append("\"" + outFile.getAbsolutePath() + "\" ");
                builder.append("\"" + outFile.getAbsolutePath() + "\"");

                cmds.add(builder.toString());

            }

            dataModel.getJobs().add(job);

        }
    }

    /**
     * Process.
     *
     * @param dataModel the data model
     * @param viewModel the view model
     * @throws InterruptedException
     * @throws Exception            the exception
     */
    public void process(DataModel dataModel, JobsStatusDto viewModel) throws InterruptedException {

        Runnable runnable = () -> {
            new File(computeOutDir(dataModel)).mkdirs();
            createJobs(dataModel);
            try {
                runJobs(dataModel, viewModel);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
    }

    /**
     * Run jobs.
     *
     * @param dataModel the data model
     * @param viewModel the view model
     * @throws InterruptedException the interrupted exception
     */
    private void runJobs(DataModel dataModel, JobsStatusDto viewModel) throws InterruptedException {
        long start = System.currentTimeMillis();
        ExecutorService executor = Executors.newFixedThreadPool(8);
        for (Job j : dataModel.getJobs()) {
            executor.execute(j);
        }

        executor.shutdown();

        while (!executor.isTerminated()) {

            statusModelUpdate(dataModel, viewModel);
            Thread.sleep(500);
        }

        executor.awaitTermination(1, TimeUnit.DAYS);

        long t = System.currentTimeMillis() - start;
        LOGGER.info("EXEC TIME:" + t);

        statusModelUpdate(dataModel, viewModel);
        viewModel.setProcessStatus(viewModel.getProcessStatus()+" czas:" + (t/1000.0));

    }

    /**
     * Gui update.
     *
     * @param dataModel the data model
     * @param viewModel the view model
     */
    private void statusModelUpdate(DataModel dataModel, JobsStatusDto viewModel) {

        int count = 0;
        int errors = 0;
        for (Job j : dataModel.getJobs()) {
            if (j.isDone()) {
                count++;
                if (j.getError() != null) {
                    errors++;
                }
            }
        }
        viewModel.setProgressValue(count);
        viewModel.setProcessStatus("" + count + "/" + dataModel.getJobs().size());
        viewModel.setErrorLabel("Błędow:" + errors);
        viewModel.setJobStarted(!(count==dataModel.getJobs().size()));
    }

    /**
     * Identify.
     *
     * @param file             the file
     * @param identifyFullPath the identify full path
     * @return the IM identify
     */
    public static IMIdentify identify(File file, String identifyFullPath) {

        IMIdentify identify = new IMIdentify();

        Execution execution = new Execution(
                "\"" + identifyFullPath + "\" -format \"%w %h\" \"" + file.getAbsolutePath() + "\"");
        try {
            execution.run();
            String l = execution.getInputLines().get(0);
            String[] tmp = l.split(" ");
            identify.setWidth(Integer.valueOf(tmp[0]));
            identify.setHeight(Integer.valueOf(tmp[1]));

        } catch (IOException e) {
            e.printStackTrace();
            throw new PhototoolRuntimeException(e.getMessage());
        }
        return identify;
    }
}
