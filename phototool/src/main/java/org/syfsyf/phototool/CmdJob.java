package org.syfsyf.phototool;

import org.apache.log4j.Logger;


// TODO: Auto-generated Javadoc
/**
 * The Class CmdJob.
 */
public class CmdJob extends AbstractJob {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(CmdJob.class);
	
	/** The cmd. */
	private String cmd;

	/**
	 * Instantiates a new cmd job.
	 *
	 * @param cmd the cmd
	 */
	public CmdJob(String cmd) {
		super();
		this.cmd=cmd;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {		
		try {			
			Execution execution = runCmd(cmd);			
			if(execution.getExitStatus()!=0){
				LOGGER.error("error:\n"+cmd+"\n"+execution.getErrorLinesStr());
				setError(execution.getErrorLinesStr());
			}
		} catch (Exception ex) {
			setError(ex.getMessage());
		}		
		setDone(true);		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {		
		return "CmdJob:"+cmd;
	}
}
