package org.syfsyf.phototool;

import org.apache.log4j.Logger;
import org.picocontainer.annotations.Inject;
import org.syfsyf.phototool.cfg.Config;
import org.syfsyf.phototool.cfg.ConfigService;
import org.syfsyf.phototool.webgui.WebServer;

import javax.swing.*;
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
    private static final Logger LOGGER = Logger.getLogger(Phototool.class);

    /**
     * The service.
     */
    @Inject
    private PhotoolFacade service;

    /**
     * The jobs.
     */
    private List<Job> jobs = new ArrayList<>();

    @Inject
    WebServer webServer;

    @Inject
    ConfigService configService;

    /**
     * Run.
     *
     * @param args the args
     * @throws Exception the exception
     */
    public void run(String[] args) throws Exception {
        webServer.start();



        boolean isDebug = java.lang.management.ManagementFactory.getRuntimeMXBean().
                getInputArguments().toString().indexOf("-agentlib:jdwp") > 0;

        if(!isDebug) {
            Config config = configService.loadConfig();

            try {
                String cmd = config.getChromeExe() + " --app=" + webServer.getServerMainUrl();
                Execution exe = new Execution(cmd);
                exe.run();
                // wait to close browser window
                webServer.stopServer();
            } catch (Exception e) {
                LOGGER.error(e);
                JOptionPane.showMessageDialog(null, e.getMessage());
                // throw new RuntimeException(e);
            }
        }
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
        System.out.println("" + count + "/" + jobs.size());

    }
}
