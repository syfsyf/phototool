package org.syfsyf.phototool.fxgui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import org.syfsyf.phototool.JobsStatus;

public class JobsStatusView implements JobsStatus {

    private StringProperty errorLabel = new SimpleStringProperty(this,"errorLabel");
    private StringProperty progressLabel = new SimpleStringProperty(this,"progressLabel");
    private IntegerProperty progressValue = new SimpleIntegerProperty(this,"progressValue");

    @Override
    public String getErrorLabel() {
        return errorLabel.get();
    }

    public StringProperty errorLabelProperty() {
        return errorLabel;
    }

    public void setErrorLabel(String errorLabel) {
        this.errorLabel.set(errorLabel);
    }

    @Override
    public String getProgressLabel() {
        return progressLabel.get();
    }

    public StringProperty progressLabelProperty() {
        return progressLabel;
    }

    public void setProgressLabel(String progressLabel) {
        this.progressLabel.set(progressLabel);
    }

    @Override
    public int getProgressValue() {
        return progressValue.get();
    }

    public IntegerProperty progressValueProperty() {
        return progressValue;
    }

    public void setProgressValue(int progressValue) {
        this.progressValue.set(progressValue);
    }
}
