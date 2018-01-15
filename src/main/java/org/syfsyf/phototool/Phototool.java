package org.syfsyf.phototool;

import org.slf4j.Logger;
import org.picocontainer.annotations.Inject;
import org.slf4j.LoggerFactory;
import org.syfsyf.phototool.cfg.Config;
import org.syfsyf.phototool.cfg.ConfigService;
import org.syfsyf.phototool.jobs.Job;
import org.syfsyf.phototool.webgui.WebServer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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
                //String cmd = config.getChromeExe() + " --user-data-dir=\"%temp%/random_name\" --app=" + webServer.getServerMainUrl();
                //String cmd = config.getChromeExe() + " --user-data-dir=\"%temp%/random_name\" " + webServer.getServerMainUrl();
                String cmd = config.getChromeExe() + " --app=" + webServer.getServerMainUrl();
                Execution exe = new Execution(cmd);
                exe.run();
                // wait to close browser window
                //webServer.stopServer();
            } catch (Exception e) {
                LOGGER.error("",e);
                JOptionPane.showMessageDialog(null, e.getMessage());
                // throw new RuntimeException(e);
            }
        }
    }
}
