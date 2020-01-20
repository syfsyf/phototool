package org.syfsyf.phototool;

import org.slf4j.Logger;
import org.picocontainer.annotations.Inject;
import org.slf4j.LoggerFactory;
import org.syfsyf.phototool.cfg.Config;
import org.syfsyf.phototool.cfg.ConfigService;
import org.syfsyf.phototool.cmds.Job;
import org.syfsyf.phototool.fxgui.PhototoolGUI;
import org.syfsyf.phototool.impl.PhotoolFacadeImpl;
import org.syfsyf.phototool.webgui.WebServer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The Class Phototool.
 */
public class Phototool {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Phototool.class);

    /**
     * The service.
     */
    @Inject
    private PhotoolFacadeImpl service;

    /**
     * The jobs.
     */
    private List<Job> jobs = new ArrayList<>();

    @Inject
    WebServer webServer;

    @Inject
    ConfigService configService;


    @Inject
    PhototoolGUI phototoolGUI;

    /**
     * Run.
     *
     * @param args the args
     * @throws Exception the exception
     */
    public void run(String[] args) throws Exception {

        Config config = configService.loadConfig();

        phototoolGUI.main(args);

        //webServer.start();

        /*
        try {
            String cmd = config.getChromeExe() + " --app=" + webServer.getServerMainUrl();
            Execution exe = new Execution(cmd);
            exe.run();

        } catch (Exception e) {
            LOGGER.error("",e);
            JOptionPane.showMessageDialog(null, e.getMessage());
            // throw new RuntimeException(e);
        }*/
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
            LOGGER.debug("" + count + "/" + jobs.size());
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
        LOGGER.info("" + count + "/" + jobs.size());

    }
}
