package org.syfsyf.phototool;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.picocontainer.annotations.Inject;
import org.syfsyf.phototool.cfg.Config;
import org.syfsyf.phototool.cfg.ConfigService;
import org.syfsyf.phototool.cfg.Profile;
import org.syfsyf.phototool.gui.ViewModel;

// TODO: Auto-generated Javadoc
/**
 * The Class Service.
 */
public class Service {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(Service.class);
		
	@Inject
	private ConfigService configService;

	/**
	 * Creates the data model.
	 *
	 * @return the data model
	 * @throws FileNotFoundException the file not found exception
	 */
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

	/**
	 * Scan files.
	 *
	 * @param cwd the cwd
	 * @return the list
	 */
	private List<File> scanFiles(File cwd) {
		List<File> files = new ArrayList<>();

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
	 * Write to view model.
	 *
	 * @param viewModel the view model
	 * @param dataModel the data model
	 */
	public void writeToViewModel(ViewModel viewModel, DataModel dataModel) {

		Profile profile = dataModel.getProfile();
		viewModel.setAutolevel(profile.isAutolevel());
		viewModel.setBorder(profile.isBorder());
		viewModel.setBorderColor(profile.getBorderColor());
		viewModel.setBorderSize(profile.getBorderSize());
		String outDirLabel = computeOutDir(dataModel);
		viewModel.setOutDirLabel(outDirLabel);
		viewModel.setResize(profile.isResize());
		viewModel.setResizeWidth(profile.getResizeWidth());
		viewModel.setDirLabel(dataModel.getCwd().getAbsolutePath());
		viewModel.setNumOfFiles("" + dataModel.getFiles().size());
		viewModel.setAddSignature(profile.isAddSignature());
		viewModel.setSigFile(profile.getSigFile());
		viewModel.setSigGeometry(profile.getSigGeometry());
		viewModel.setSigGravity(profile.getSigGravity());
		viewModel.setSigResize(profile.getSigResize());
		viewModel.setCustomParams(profile.getCustomParams());
		
		//viewModel.setAddSignature(dataModel.get);

	}

	/**
	 * Write to data model.
	 *
	 * @param dataModel the data model
	 * @param viewModel the view model
	 */
	public void writeToDataModel(DataModel dataModel, ViewModel viewModel) {

		Profile profile = dataModel.getProfile();
		
		profile.setAutolevel(viewModel.isAutolevel());
		profile.setBorder(viewModel.isBorder());
		profile.setBorderColor(viewModel.getBorderColor());
		profile.setBorderSize(viewModel.getBorderSize());
		profile.setResize(viewModel.isResize());
		profile.setResizeWidth(viewModel.getResizeWidth());
		profile.setAddSignature(viewModel.isAddSignature());
		profile.setSigFile(viewModel.getSigFile());
		profile.setSigGeometry(viewModel.getSigGeometry());
		profile.setSigGravity(viewModel.getSigGravity());
		profile.setSigResize(viewModel.getSigResize());
		profile.setCustomParams(viewModel.getCustomParams());
		
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
			
			IMIdentify identify=identify(f, config.getImgMagicIdentify());
			LOGGER.debug("identify:"+identify);
			
			List<String> cmds = new ArrayList<String>();
			Job job = new MultiCmdJob(cmds);

			StringBuilder builder = new StringBuilder();
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
				builder.append(" -bordercolor " + toHexColor(profile.getBorderColor()));
				builder.append(" -border " + s + "x" + s);

			}
			if (profile.isAutolevel()) {
				builder.append(" -auto-level");
			}
			
			if(profile.getCustomParams()!=null && !"".equals(profile.getCustomParams())){
				builder.append(" "+profile.getCustomParams()+" ");
			}
			
			File outFile = new File(outDir, f.getName());
			builder.append(" \"" + outFile.getAbsolutePath() + "\"");
			LOGGER.info("cmd:" + builder.toString());

			cmds.add(builder.toString());

			if (profile.isAddSignature()) {
				
				builder = new StringBuilder();
				builder.append(config.getImgMagicComposite());
				builder.append(" -gravity "+profile.getSigGravity()  +" -geometry "+profile.getSigGeometry()+" ");
				
				builder.append("( \""+profile.getSigFile()+"\" -resize \""+profile.getSigResize()+"\" ) ");				
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
	 * @throws Exception the exception
	 */
	public void process(DataModel dataModel, ViewModel viewModel) throws Exception {

		createJobs(dataModel);
		configService.saveProfile(dataModel.getProfile());
		new File(computeOutDir(dataModel)).mkdirs();
		runJobs(dataModel, viewModel);

	}

	/**
	 * Run jobs.
	 *
	 * @param dataModel the data model
	 * @param viewModel the view model
	 * @throws InterruptedException the interrupted exception
	 */
	private void runJobs(DataModel dataModel, ViewModel viewModel) throws InterruptedException {
		long start = System.currentTimeMillis();
		ExecutorService executor = Executors.newFixedThreadPool(8);
		for (Job j : dataModel.getJobs()) {
			executor.execute(j);
		}

		executor.shutdown();

		while (!executor.isTerminated()) {

			guiUpdate(dataModel, viewModel);
			Thread.sleep(500);
		}

		executor.awaitTermination(1, TimeUnit.DAYS);

		long t = System.currentTimeMillis() - start;
		LOGGER.info("EXEC TIME:" + t);

		guiUpdate(dataModel, viewModel);

	}

	/**
	 * Gui update.
	 *
	 * @param dataModel the data model
	 * @param viewModel the view model
	 */
	private void guiUpdate(DataModel dataModel, ViewModel viewModel) {

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
		viewModel.setProgressLabel("" + count + "/" + dataModel.getJobs().size());
		viewModel.setErrorLabel("Błędow:" + errors);

		// gui.getProgressBar().setValue(count);
		// gui.getProgresLabel().setText("" + count + "/" + jobs.size());
		// gui.getErrorLabel().setText("Błędów:" + errors);

	}
	
	/**
	 * Identify.
	 *
	 * @param file the file
	 * @param identifyFullPath the identify full path
	 * @return the IM identify
	 */
	public static IMIdentify identify(File file,String identifyFullPath){
		
		IMIdentify identify=new IMIdentify();
		
		Execution execution=new Execution("\""+identifyFullPath+"\" -format \"%w %h\" \""+file.getAbsolutePath()+"\"");
		try {
			execution.run();
			String l = execution.getInputLines().get(0);
			String[] tmp = l.split(" ");
			identify.setWidth(Integer.valueOf(tmp[0]));
			identify.setHeight(Integer.valueOf(tmp[1]));
			
		} catch (IOException e) {			
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}		
		return identify;
	}
}
