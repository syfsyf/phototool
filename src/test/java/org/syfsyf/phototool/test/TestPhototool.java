package org.syfsyf.phototool.test;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.picocontainer.MutablePicoContainer;
import org.slf4j.LoggerFactory;
import org.syfsyf.phototool.DataModel;
import org.syfsyf.phototool.Main;
import org.syfsyf.phototool.PhotoolFacade;
import org.syfsyf.phototool.cfg.Profile;
import org.syfsyf.phototool.gui.JobsStatusDto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestPhototool {


    private static final Logger LOGGER = LoggerFactory.getLogger(TestPhototool.class);


    private File sample;
    MutablePicoContainer container = Main.createPicoContainer();
    protected PhotoolFacade phototoolFacade;


    @Before
    public void before() {
        LOGGER.info("before");
        this.container = Main.createPicoContainer();
        this.phototoolFacade = container.getComponent(PhotoolFacade.class);

    }

    @Before
    public void prepareSampleDir() throws IOException {

        final String sampleDir = "sample";
        this.sample = new File(sampleDir);
        FileUtils.deleteDirectory(sample);
        sample.mkdirs();

        File srcDir = new File("test_samples/sample");
        FileUtils.copyDirectory(srcDir, sample);
    }

    @Test
    public void test1() throws Exception {


        DataModel dataModel = phototoolFacade.createDataModel(sample);

        Profile profile = dataModel.getProfile();
        profile.setResizeWidth(250);
        profile.setAddSignature(true);
        profile.setSigFile(new File("test_samples/sig/sig.png").getAbsolutePath());
        profile.setSigResize("x35");

        phototoolFacade.createJobs(dataModel);

        LOGGER.info("jobs:" + dataModel.getJobs().size());


        JobsStatusDto viewModel = new JobsStatusDto();
        phototoolFacade.process(dataModel, viewModel);

    }
    @Test
    public void testResizeOption() throws Exception {

        DataModel dataModel = phototoolFacade.createDataModel(sample);

        Profile profile = dataModel.getProfile();
        System.out.println("resize:"+profile.isResize());
        //profile.setResize(false);
        phototoolFacade.createJobs(dataModel);
        JobsStatusDto viewModel = new JobsStatusDto();
        phototoolFacade.processAndWait(dataModel, viewModel);

    }



}
