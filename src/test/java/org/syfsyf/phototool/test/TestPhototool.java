package org.syfsyf.phototool.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.picocontainer.MutablePicoContainer;
import org.syfsyf.phototool.DataModel;
import org.syfsyf.phototool.Main;
import org.syfsyf.phototool.PhotoolFacade;
import org.syfsyf.phototool.cfg.Profile;
import org.syfsyf.phototool.gui.ViewModel;

public class TestPhototool {
	
	
	private static final Logger LOGGER = Logger.getLogger(TestPhototool.class);

	
	private File sample;	
	MutablePicoContainer container=Main.createPicoContainer();
	protected PhotoolFacade phototoolFacade;
		
	
	@Before
	public void before()
	{
		this.container=Main.createPicoContainer();
		this.phototoolFacade=container.getComponent(PhotoolFacade.class);
		
	}
	

	protected void prepareSampleDir() throws IOException{
		
		final String sampleDir="sample";
		this.sample = new File(sampleDir);
		FileUtils.deleteDirectory(sample);
		sample.mkdirs();
								
		File srcDir=new File("src/test/resources/sample");
		FileUtils.copyDirectory(srcDir, sample);
				
				
	}
	
	@Test
	public void test1() throws Exception{
				
		prepareSampleDir();
		DataModel dataModel = phototoolFacade.createDataModel(sample);
		
		System.out.println(dataModel.getFiles().size());
		
		Profile profile = dataModel.getProfile();
		profile.setResizeWidth(250);
		profile.setAddSignature(true);
		profile.setSigFile(new File("src/test/resources/sig/sig.png").getAbsolutePath());
		profile.setSigResize("x35");
		
		phototoolFacade.createJobs(dataModel);
		
		LOGGER.info("jobs:"+dataModel.getJobs().size());
		
		
		ViewModel viewModel=new ViewModel();
		phototoolFacade.process(dataModel, viewModel);

	}
	
	
}
