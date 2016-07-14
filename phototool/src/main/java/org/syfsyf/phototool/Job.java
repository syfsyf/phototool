package org.syfsyf.phototool;

// TODO: Auto-generated Javadoc
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
