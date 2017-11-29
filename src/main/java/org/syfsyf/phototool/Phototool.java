package org.syfsyf.phototool;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.picocontainer.annotations.Inject;
import org.syfsyf.phototool.cfg.Profile;
import org.syfsyf.phototool.gui.GUI;
import org.syfsyf.phototool.gui.ViewModel;
import org.syfsyf.phototool.webgui.WebServer;

import com.thoughtworks.xstream.XStream;

// TODO: Auto-generated Javadoc
/**
 * The Class Phototool.
 */
public class Phototool {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(Phototool.class);
	
	/** The data model. */
	private DataModel dataModel;
	
	/** The service. */
	@Inject
	private Service service;
	
	/** The jobs. */
	private List<Job> jobs = new ArrayList<>();
	
	@Inject
	WebServer webServer;
	
	
	/** The gui. */
	@Inject
	private GUI gui;
		
	/**
	 * Run.
	 *
	 * @param args the args
	 * @throws Exception the exception
	 */
	public void run(String[] args) throws Exception {
		
		/*
		dataModel=getService().createDataModel();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				runGUI();
			}
		});
		*/		
		webServer.start();
		
	}

	/**
	 * Run GUI.
	 */
	protected void runGUI() {
		try {			
			gui.getRunButton().addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					onGo();
				}
			});
			final ViewModel model = new ViewModel();			
			getService().writeToViewModel(model,dataModel);			
			gui.setViewModel(model);
			
			model.addPropertyChangeListener(new PropertyChangeListener() {
				
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					LOGGER.debug("property changed!"+evt);
					getService().writeToDataModel(dataModel, model);
					String outDirLabel=getService().computeOutDir(dataModel);
					model.setOutDirLabel(outDirLabel);
					
				}
			});
			
			gui.getFrmPhotoTool().setVisible(true);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * On go.
	 */
	protected void onGo() {
		
		//createJobs(profile);
		gui.getRunButton().setEnabled(false);
		Thread queryThread = new Thread() {
			public void run() {
				try {
					ViewModel viewModel=gui.getViewModel();					
					getService().writeToDataModel(dataModel,viewModel);
					getService().process(dataModel, viewModel);
					gui.getRunButton().setEnabled(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		queryThread.start();
	}

	

	
	/**
	 * Run jobs thread.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
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

	private Service getService() {
		return service;
	}

	

	
}
