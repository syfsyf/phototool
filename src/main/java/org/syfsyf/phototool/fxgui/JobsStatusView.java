package org.syfsyf.phototool.fxgui;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import org.syfsyf.phototool.JobsStatus;

public class JobsStatusView implements JobsStatus {

    private ProgressBar progressBar;
    private Label errorLabel;

    public JobsStatusView(ProgressBar progressBar, Label errorLabel) {
        this.progressBar = progressBar;
        this.errorLabel = errorLabel;
    }

    @Override
    public String getErrorLabel() {
        return errorLabel.getText();
    }
    public void setErrorLabel(String errorLabelValue) {

        Platform.runLater(() -> errorLabel.setText(errorLabelValue));
    }


    public void setProgressLabel(String progressLabelValue) {
        Platform.runLater(() -> errorLabel.setText(progressLabelValue));
    }

    @Override
    public double getProgressValue() {
        return progressBar.getProgress();
    }


    @Override
    public void setProgressValue(double progressValue) {
        Platform.runLater(() -> progressBar.setProgress(progressValue));
    }
}
