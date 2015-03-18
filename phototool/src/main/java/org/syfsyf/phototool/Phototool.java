package org.syfsyf.phototool;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.syfsyf.phototool.gui.GUI;

import com.thoughtworks.xstream.XStream;

public class Phototool {

	private static final Logger LOGGER = Logger.getLogger(Phototool.class);
	private File cwd;
	private File outDir;
	private List<File> files;
	private List<Job> jobs = new ArrayList<>();
	private static final XStream X_STREAM;
	static {
		X_STREAM = new XStream();
		X_STREAM.autodetectAnnotations(true);
		X_STREAM.processAnnotations(new Class[] { Profile.class });
	}

	private GUI gui;
	private Profile profile;

	public void run(String[] args) throws Exception {

		cwd = new File(System.getProperty("user.dir"));
		profile = loadProfile();
		outDir = new File(cwd.getAbsolutePath() + "/" + profile.getOutDirName() + profile.getResizeWidth());
		files = scanFiles();

		boolean interacrive = true;
		if (interacrive) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					runGUI();
				}
			});
		} else {
			createJobs(profile);
		}

	}

	protected void runGUI() {
		try {
			gui = new GUI();
			gui.getRunButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					onGo();
				}
			});
			
			
			
			
			bindGUI();
			gui.getFrmPhotoTool().setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void onGo() {

		createJobs(profile);
		gui.getRunButton().setEnabled(false);
		Thread queryThread = new Thread() {
			public void run() {
				try {
					runJobsThreadGUI();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		queryThread.start();
	}

	protected void bindGUI() {

		gui.getDirLabel().setText(cwd.getAbsolutePath());
		gui.getOutDirLabel().setText(outDir.getAbsolutePath());
		gui.getNumOfFileLabel().setText("" + files.size());
		gui.getResizeWidth().setText("" + profile.getResizeWidth());
		gui.getProgressBar().setMaximum(files.size());
		gui.getProgresLabel().setText("0/" + files.size());
		gui.getErrorLabel().setText("Błędów:0");

	}

	private File getConfigFile() {
		return new File(System.getProperty("user.home") + "/.phototool/config.xml");
	}

	private Profile loadProfile() throws FileNotFoundException {

		Profile profile = null;
		File cfgFile = getConfigFile();
		if (!cfgFile.exists()) {
			profile = new Profile();
			saveProfile(profile);
		} else {
			profile = (Profile) X_STREAM.fromXML(cfgFile);
		}
		return profile;
	}

	private void saveProfile(Profile profile) throws FileNotFoundException {

		File cfg = getConfigFile();
		cfg.getParentFile().mkdirs();
		X_STREAM.toXML(profile, new FileOutputStream(getConfigFile()));
	}

	public void runJobsThread() throws InterruptedException {

		long start = System.currentTimeMillis();
		ExecutorService executor = Executors.newFixedThreadPool(8);
		for (Job j : jobs) {
			executor.execute(j);
		}
		// This will make the executor accept no new threads
		// and finish all existing threads in the queue
		executor.shutdown();
		// Wait until all threads are finish
		while (!executor.isTerminated()) {

			int count = 0;
			for (Job j : jobs) {
				if (j.isDone()) {
					count++;
				}
			}
			System.out.println("" + count + "/" + jobs.size());
			Thread.sleep(500);

		}

		executor.awaitTermination(1, TimeUnit.DAYS);

		// System.out.println("Finished all threads");
		long t = System.currentTimeMillis() - start;
		LOGGER.info("EXEC TIME:" + t);

		int count = 0;
		for (Job j : jobs) {
			if (j.isDone()) {
				count++;
			}
		}
		System.out.println("" + count + "/" + jobs.size());

	}

	public void runJobsThreadGUI() throws InterruptedException {

		long start = System.currentTimeMillis();
		ExecutorService executor = Executors.newFixedThreadPool(8);
		for (Job j : jobs) {
			executor.execute(j);
		}

		executor.shutdown();

		while (!executor.isTerminated()) {

			guiUpdate();
			Thread.sleep(500);
		}

		executor.awaitTermination(1, TimeUnit.DAYS);

		long t = System.currentTimeMillis() - start;
		LOGGER.info("EXEC TIME:" + t);

		int count = 0;
		for (Job j : jobs) {
			if (j.isDone()) {
				count++;
			}
		}
		System.out.println("" + count + "/" + jobs.size());
		guiUpdate();
		try {
			saveProfile(profile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void guiUpdate() {

		int count = 0;
		int errors = 0;
		for (Job j : jobs) {
			if (j.isDone()) {
				count++;
				if (j.getError() != null) {
					errors++;
				}
			}
		}
		gui.getProgressBar().setValue(count);
		gui.getProgresLabel().setText("" + count + "/" + jobs.size());
		gui.getErrorLabel().setText("Błędów:" + errors);

	}

	private void runJobs() throws Exception {
		/*
		 * for(int i=0;i<5;i++){ jobs.get(i).run(); }
		 */
		long start = System.currentTimeMillis();
		for (Job j : jobs) {
			j.run();
		}
		long t = System.currentTimeMillis() - start;
		LOGGER.info("EXEC TIME:" + t);
		// EXEC TIME:55346

	}

	private void createJobs(Profile profile) {
		for (File f : files) {
			Job job = null;
			if (profile.isResize()) {
				job = new ResizeJob(profile.getImgMagicConvert(), f, new File(outDir, f.getName()), profile.getResizeWidth());
			}
			jobs.add(job);
		}
	}

	private List<File> scanFiles() {
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

}
