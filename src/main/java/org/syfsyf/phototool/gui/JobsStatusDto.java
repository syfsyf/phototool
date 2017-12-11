package org.syfsyf.phototool.gui;

public class JobsStatusDto {

    private String errorLabel = "";
    private String processStatus = "";
    private int progressValue = 0;

    private boolean jobStarted;

    public String getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(String errorLabel) {
        this.errorLabel = errorLabel;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    public int getProgressValue() {
        return progressValue;
    }

    public void setProgressValue(int progressValue) {
        this.progressValue = progressValue;
    }

    public boolean isJobStarted() {
        return jobStarted;
    }

    public void setJobStarted(boolean jobStarted) {
        this.jobStarted = jobStarted;
    }
}
