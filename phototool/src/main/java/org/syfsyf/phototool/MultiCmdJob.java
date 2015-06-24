package org.syfsyf.phototool;

import java.util.List;

import org.apache.log4j.Logger;

public class MultiCmdJob extends AbstractJob {

	private static final Logger LOGGER = Logger.getLogger(MultiCmdJob.class);
	private List<String> cmds;

	public MultiCmdJob(List<String> cmds) {
		super();
		this.cmds = cmds;
	}
	
	@Override
	public void run() {
		try {
			for (String cmd : cmds) {
				Execution execution = runCmd(cmd);
				if (execution.getExitStatus() != 0) {
					LOGGER.error("error:\n" + cmd + "\n" + execution.getErrorLinesStr());
					setError(execution.getErrorLinesStr());
				}
			}
		} catch (Exception ex) {
			setError(ex.getMessage());
		}
		setDone(true);
	}
	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder();
		builder.append("MultiCmdJob\n");
		if(cmds!=null){
			for(String cmd:cmds){
				builder.append("\t");
				builder.append(cmd);
				builder.append("\n");
			}
		}
		return builder.toString();
	}
}
