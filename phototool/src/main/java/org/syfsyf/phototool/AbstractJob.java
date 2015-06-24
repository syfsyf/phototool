package org.syfsyf.phototool;

import java.io.IOException;

import org.apache.log4j.Logger;

public abstract class AbstractJob implements Job {

	private static final Logger LOGGER = Logger.getLogger(AbstractJob.class);
	private String error;
	private boolean done = false;

	protected Execution runCmd(String cmd) throws IOException {
		Execution execution = new Execution(cmd);
		execution.run();
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
