package org.syfsyf.phototool;

public interface Job extends Runnable{
	
	boolean isDone();
	String getError();
	
}
