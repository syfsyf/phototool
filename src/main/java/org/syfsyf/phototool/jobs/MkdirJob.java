package org.syfsyf.phototool.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * Created by mb on 2018-01-15.
 */
public class MkdirJob extends AbstractJob{

    private static final Logger LOGGER = LoggerFactory.getLogger(MkdirJob.class);

    private File dir;

    public MkdirJob(File dir) {
        this.dir = dir;
        setParalel(false);
    }

    @Override
    public void run() {
        LOGGER.info("create dir:"+dir.getAbsolutePath());
        dir.mkdirs();
        LOGGER.info("dir created"+dir.getAbsolutePath());
    }
}
