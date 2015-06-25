package org.syfsyf.phototool;

import javax.swing.UIManager;

import org.apache.log4j.BasicConfigurator;

import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;




public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BasicConfigurator.configure();
		UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
		new Phototool().run(args);				
	}	
}
