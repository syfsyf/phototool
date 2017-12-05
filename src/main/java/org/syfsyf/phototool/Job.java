package org.syfsyf.phototool;

/**
 * The Interface Job.
 */
public interface Job extends Runnable{
	
	/**
	 * Checks if is done.
	 *
	 * @return true, if is done
	 */
	boolean isDone();
	
	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	String getError();
	
}
