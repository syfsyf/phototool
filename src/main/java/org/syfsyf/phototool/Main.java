package org.syfsyf.phototool;

import org.apache.log4j.BasicConfigurator;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.syfsyf.phototool.cfg.impl.ConfigServiceImpl;
import org.syfsyf.phototool.fxgui.PhototoolGUI;
import org.syfsyf.phototool.utils.JsonServiceImpl;
import org.syfsyf.phototool.webgui.WebServer;
import org.syfsyf.phototool.webgui.impl.ApiImpl;
import org.syfsyf.phototool.webgui.impl.WebApiImpl;

/**
 * The Class Main.
 */
public class Main {

    /**
     * The main method.
     *
     * @param args the arguments
     * @throws Exception the exception
     */
    public static void main(String[] args){

        BasicConfigurator.configure();
        PhototoolGUI.main(args);
    }


}
