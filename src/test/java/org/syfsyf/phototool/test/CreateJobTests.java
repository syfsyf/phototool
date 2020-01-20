package org.syfsyf.phototool.test;

import org.apache.log4j.BasicConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.syfsyf.phototool.DataModel;
import org.syfsyf.phototool.impl.PhotoolFacadeImpl;
import org.syfsyf.phototool.gui.JobsStatusDto;

import java.io.File;

import static org.junit.Assert.assertNotNull;

public class CreateJobTests {


    private static File TEST_FILE_DIR = new File("d:\\mb\\photootool\\test files\\");

    @Before
    public void setUp() {
        BasicConfigurator.configure();
        System.setProperty("user.dir", TEST_FILE_DIR.getAbsolutePath());
    }

    @Test
    public void test() throws Exception {

        PhotoolFacadeImpl service = new PhotoolFacadeImpl();

        DataModel model = service.createDataModel();
        model.getProfile().setAddSignature(true);
        model.getProfile().setSigFile("c:\\Users\\user\\.phototool\\sigs\\podpis.png");
        model.getProfile().setSigResize("x15");

        //service.writeToDataModel(model, service.);

        service.createJobs(model);
        assertNotNull(model.getJobs());
        System.out.println(model.getJobs());
        service.process(model, new JobsStatusDto());


        //fail("Not yet implemented");
    }


}
