package org.syfsyf.phototool.jobs;

import org.picocontainer.annotations.Inject;
import org.syfsyf.phototool.DataModel;
import org.syfsyf.phototool.imagemagic.IMIdentify;
import org.syfsyf.phototool.cfg.Config;
import org.syfsyf.phototool.cfg.Profile;
import org.syfsyf.phototool.imagemagic.ImageMagicTool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by mb on 2018-01-15.
 */

public class JobsCreatorImpl implements JobsCreator{


    @Inject
    private ImageMagicTool imageMagicTool;

    /**
     * Compute out dir.
     *
     * @param model the model
     * @return the string
     */
    protected String computeOutDir(DataModel model) {
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

    @Override
    public List<Job> create(DataModel model) throws FileNotFoundException {

        List<Job> result=new ArrayList<>();

        // utworzenie katalogu wyjsciowego
        Optional<Job> outDir = createOutputDir(model);
        if(outDir.isPresent()){
            result.add(outDir.get());
        }
        result.addAll(createGeoTags(model));

        return result;
    }

    private Collection<? extends Job> createGeoTags(DataModel model) throws FileNotFoundException {


        List<Job> jobs=new ArrayList<>();

        Profile profile = model.getProfile();

        if(!profile.isGeoTag()){
            return jobs;
        }
        Config config = model.getConfig();
        for (File f : model.getFiles()) {

            //IMIdentify identify = imageMagicTool.identify(f);
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
                jobs.add(new CmdJob(builder.toString()));
            }
        }
        return jobs;

    }

    String doubleToString(Double d) {

        String res = Double.toString(d);
        return res.replaceAll("\\,", ".");
    }

    private Optional<Job> createOutputDir(DataModel model) {

        Profile profile = model.getProfile();
        //return Optional.of()
        if(profile.isResize()){
            String outDir = computeOutDir(model);
            MkdirJob mkdirJob=new MkdirJob(new File(outDir));
            return Optional.of(mkdirJob);
        }
        return Optional.empty();

    }
}
