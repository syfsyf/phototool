package org.syfsyf.phototool.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.syfsyf.phototool.DataModel;
import org.syfsyf.phototool.PhotoolFacade;
import org.syfsyf.phototool.gui.ViewModel;

public class CreateJobTests {

	
	public static File TEST_FILE_DIR=new File("d:\\mb\\photootool\\test files\\");
	
	@Before
	public void setUp() throws Exception {
		BasicConfigurator.configure();
		System.setProperty("user.dir",TEST_FILE_DIR.getAbsolutePath());
	}

	@Test
	public void test() throws Exception {
		
		PhotoolFacade service=new PhotoolFacade();
		
		DataModel model = service.createDataModel();
		model.getProfile().setAddSignature(true);
		model.getProfile().setSigFile("c:\\Users\\user\\.phototool\\sigs\\podpis.png");
		model.getProfile().setSigResize("x15");
		
		//service.writeToDataModel(model, service.);
		
		service.createJobs(model);
		assertNotNull( model.getJobs());		
		System.out.println(model.getJobs());
		service.process(model, new ViewModel());
		
		
		
		//fail("Not yet implemented");
	}
	

}
