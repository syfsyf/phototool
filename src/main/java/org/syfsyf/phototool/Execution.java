package org.syfsyf.phototool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Execution.
 */
public class Execution {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Execution.class);

    /**
     * The cmd.
     */
    private String cmd;

    /**
     * The exit status.
     */
    private int exitStatus;

    /**
     * The input lines.
     */
    private List<String> inputLines;

    /**
     * The error lines.
     */
    private List<String> errorLines;

    /**
     * Instantiates a new execution.
     *
     * @param cmd the cmd
     */
    public Execution(String cmd) {
        super();
        this.cmd = cmd;
    }

    /**
     * Gets the cmd.
     *
     * @return the cmd
     */
    public String getCmd() {
        return cmd;
    }

    /**
     * Sets the cmd.
     *
     * @param cmd the new cmd
     */
    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    /**
     * Gets the exit status.
     *
     * @return the exit status
     */
    public int getExitStatus() {
        return exitStatus;
    }

    /**
     * Sets the exit status.
     *
     * @param exitStatus the new exit status
     */
    public void setExitStatus(int exitStatus) {
        this.exitStatus = exitStatus;
    }

    /**
     * Gets the input lines.
     *
     * @return the input lines
     */
    public List<String> getInputLines() {
        return inputLines;
    }

    /**
     * Sets the input lines.
     *
     * @param inputLines the new input lines
     */
    public void setInputLines(List<String> inputLines) {
        this.inputLines = inputLines;
    }

    /**
     * Gets the error lines.
     *
     * @return the error lines
     */
    public List<String> getErrorLines() {
        return errorLines;
    }

    /**
     * Sets the error lines.
     *
     * @param errorLines the new error lines
     */
    public void setErrorLines(List<String> errorLines) {
        this.errorLines = errorLines;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "\ncmd:" + cmd + "\nexit:" + getExitStatus() + "\nerror:" + errorLines;

    }

    /**
     * Gets the error lines str.
     *
     * @return the error lines str
     */
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

    /**
     * Run.
     *
     * @throws IOException Signals that an I/O exception has occurred.
     */
    public void run() throws IOException {
        String line;

        LOGGER.debug("executing:" + cmd);

        Process p = Runtime.getRuntime().exec(cmd);

        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
        List<String> lines = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            lines.add(line);
        }
        setInputLines(lines);
        input.close();

        input = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        List<String> erros = new ArrayList<>();
        while ((line = input.readLine()) != null) {
            erros.add(line);
        }
        setErrorLines(erros);
        input.close();
        setExitStatus(p.exitValue());
    }

}