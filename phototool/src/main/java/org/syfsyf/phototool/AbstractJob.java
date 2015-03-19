package org.syfsyf.phototool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;


public abstract class AbstractJob implements Job {

	private static final Logger LOGGER = Logger.getLogger(AbstractJob.class);
	private String error;
	private boolean done=false;

	class Execution {

		private String cmd;
		private int exitStatus;
		private List<String> inputLines;
		private List<String> errorLines;

		public String getCmd() {
			return cmd;
		}

		public void setCmd(String cmd) {
			this.cmd = cmd;
		}

		public int getExitStatus() {
			return exitStatus;
		}

		public void setExitStatus(int exitStatus) {
			this.exitStatus = exitStatus;
		}

		public List<String> getInputLines() {
			return inputLines;
		}

		public void setInputLines(List<String> inputLines) {
			this.inputLines = inputLines;
		}

		public List<String> getErrorLines() {
			return errorLines;
		}

		public void setErrorLines(List<String> errorLines) {
			this.errorLines = errorLines;
		}
		
		@Override
		public String toString() {
			return "\ncmd:"+cmd+"\nexit:"+getExitStatus()+"\nerror:"+errorLines;
						
		}
		public String getErrorLinesStr()
		{
			if(getErrorLines()==null){
				return null;
			}
			StringBuilder builder=new StringBuilder();
			for(String s:getErrorLines()){
				builder.append(s);
			}
			return builder.toString();
		}

	}

	public String convertExe() {
		return "d:\\progs\\ImageMagick-6.9.0-Q16\\convert.exe";
		//return "convert.exe";
	}

	protected Execution runCmd(String cmd) throws IOException {
		Execution execution = new Execution();
		execution.setCmd(cmd);

		//LOGGER.debug("exec: " + cmd);

		String line;

		Process p = Runtime.getRuntime().exec(cmd);

		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		List<String> inputLines = new ArrayList<>();
		while ((line = input.readLine()) != null) {
			inputLines.add(line);
		}
		execution.setInputLines(inputLines);
		input.close();

		input = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		List<String> errorLines = new ArrayList<>();
		while ((line = input.readLine()) != null) {
			errorLines.add(line);
		}
		execution.setErrorLines(errorLines);
		input.close();
		execution.setExitStatus(p.exitValue());

		return execution;

	}
	@Override
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}

}
