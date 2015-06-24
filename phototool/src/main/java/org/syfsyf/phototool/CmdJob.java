package org.syfsyf.phototool;

import org.apache.log4j.Logger;


public class CmdJob extends AbstractJob {

	private static final Logger LOGGER = Logger.getLogger(CmdJob.class);
	private String cmd;

	public CmdJob(String cmd) {
		super();
		this.cmd=cmd;
	}

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
	@Override
	public String toString() {		
		return "CmdJob:"+cmd;
	}
}
