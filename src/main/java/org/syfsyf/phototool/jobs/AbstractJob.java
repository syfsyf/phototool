package org.syfsyf.phototool.jobs;

import org.syfsyf.phototool.Execution;
import org.syfsyf.phototool.jobs.Job;

import java.io.IOException;

/**
 * The Class AbstractJob.
 */
public abstract class AbstractJob implements Job {

    /**
     * The error.
     */
    private String error;

    /**
     * The done.
     */
    private boolean done = false;


    private boolean paralel=true;

    /**
     * Run cmd.
     *
     * @param cmd the cmd
     * @return the execution
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected Execution runCmd(String cmd) throws IOException {
        Execution execution = new Execution(cmd);
        execution.run();
        return execution;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.syfsyf.phototool.Job#getError()
     */
    @Override
    public String getError() {
        return error;
    }

    /**
     * Sets the error.
     *
     * @param error the new error
     */
    public void setError(String error) {
        this.error = error;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.syfsyf.phototool.Job#isDone()
     */
    @Override
    public boolean isDone() {
        return done;
    }

    /**
     * Sets the done.
     *
     * @param done the new done
     */
    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean isParalel() {
        return paralel;
    }

    public void setParalel(boolean paralel) {
        this.paralel = paralel;
    }
}
