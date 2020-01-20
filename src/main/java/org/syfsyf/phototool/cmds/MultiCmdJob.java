package org.syfsyf.phototool.cmds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * The Class MultiCmdJob.
 */
public class MultiCmdJob extends AbstractJob {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MultiCmdJob.class);

    /**
     * The cmds.
     */
    private List<String> cmds;

    /**
     * Instantiates a new multi cmd job.
     *
     * @param cmds the cmds
     */
    public MultiCmdJob(List<String> cmds) {
        super();
        this.cmds = cmds;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Runnable#run()
     */
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

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("MultiCmdJob\n");
        if (cmds != null) {
            for (String cmd : cmds) {
                builder.append("\t");
                builder.append(cmd);
                builder.append("\n");
            }
        }
        return builder.toString();
    }
}
