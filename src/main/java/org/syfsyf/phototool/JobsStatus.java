package org.syfsyf.phototool;

public interface JobsStatus {
    String getErrorLabel();
    void setErrorLabel(String errorLabel);
    double getProgressValue();
    void setProgressValue(double percentValue);
}
