package org.syfsyf.phototool.gui;

import org.syfsyf.phototool.JobsStatus;

public class JobsStatusDto implements JobsStatus {

    private String errorLabel = "";
    private String progressLabel = "";
    private double progressValue = 0;

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

    public double getProgressValue() {
        return progressValue;
    }

    public void setProgressValue(double progressValue) {
        this.progressValue = progressValue;
    }

}
