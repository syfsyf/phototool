package org.syfsyf.phototool.gui;

import org.syfsyf.phototool.JobsStatus;

public class JobsStatusDto implements JobsStatus {

    private String errorLabel = "";
    private String progressLabel = "";
    private int progressValue = 0;

    public String getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(String errorLabel) {
        this.errorLabel = errorLabel;
    }

    public String getProgressLabel() {
        return progressLabel;
    }

    public void setProgressLabel(String progressLabel) {
        this.progressLabel = progressLabel;
    }

    public int getProgressValue() {
        return progressValue;
    }

    public void setProgressValue(int progressValue) {
        this.progressValue = progressValue;
    }

}
