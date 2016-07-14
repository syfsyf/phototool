package org.syfsyf.phototool;

import javax.swing.UIManager;

import org.apache.log4j.BasicConfigurator;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;




// TODO: Auto-generated Javadoc
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
		UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
		new Phototool().run(args);				
	}	
}
