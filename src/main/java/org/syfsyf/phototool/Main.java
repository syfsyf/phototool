package org.syfsyf.phototool;

import org.apache.log4j.BasicConfigurator;
import org.syfsyf.phototool.fxgui.PhototoolGUI;


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
