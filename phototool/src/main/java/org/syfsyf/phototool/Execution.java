package org.syfsyf.phototool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

class Execution {

	private static final Logger LOGGER = Logger.getLogger(Execution.class);

	private String cmd;
	private int exitStatus;
	private List<String> inputLines;
	private List<String> errorLines;

	public Execution(String cmd) {
		super();
		this.cmd = cmd;
	}

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
		return "\ncmd:" + cmd + "\nexit:" + getExitStatus() + "\nerror:" + errorLines;

	}

	public String getErrorLinesStr() {
		if (getErrorLines() == null) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		for (String s : getErrorLines()) {
			builder.append(s);
		}
		return builder.toString();
	}

	public void run() throws IOException {
		String line;
		
		LOGGER.debug("executing:"+cmd);

		Process p = Runtime.getRuntime().exec(cmd);

		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		List<String> inputLines = new ArrayList<>();
		while ((line = input.readLine()) != null) {
			inputLines.add(line);
		}
		setInputLines(inputLines);
		input.close();

		input = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		List<String> errorLines = new ArrayList<>();
		while ((line = input.readLine()) != null) {
			errorLines.add(line);
		}
		setErrorLines(errorLines);
		input.close();
		setExitStatus(p.exitValue());
	}

}