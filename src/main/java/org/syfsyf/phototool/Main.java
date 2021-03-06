package org.syfsyf.phototool;

import org.apache.log4j.BasicConfigurator;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.syfsyf.phototool.cfg.impl.ConfigServiceImpl;
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
    public static void main(String[] args) throws Exception {

        BasicConfigurator.configure();
        MutablePicoContainer pico = createPicoContainer();
        Phototool phototool = pico.getComponent(Phototool.class);
        phototool.run(args);

    }

    public static MutablePicoContainer createPicoContainer() {

        MutablePicoContainer pico = new DefaultPicoContainer();

        pico.addComponent(ConfigServiceImpl.class);
        pico.addComponent(PhotoolFacade.class);
        pico.addComponent(Phototool.class);
        pico.addComponent(WebServer.class);
        pico.addComponent(JsonServiceImpl.class);
        pico.addComponent(ApiImpl.class);
        pico.addComponent(WebApiImpl.class);

        return pico;
    }
}
