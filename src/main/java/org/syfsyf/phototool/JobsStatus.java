package org.syfsyf.phototool;

public interface JobsStatus {
    String getErrorLabel();
    void setErrorLabel(String errorLabel);
    String getProgressLabel();
    void setProgressLabel(String progressLabel);
    int getProgressValue();
    void setProgressValue(int progressValue);
}
