package org.syfsyf.phototool;

import org.apache.log4j.BasicConfigurator;




public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BasicConfigurator.configure();		
		new Phototool().run(args);				
	}	
}
