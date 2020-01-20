package org.syfsyf.phototool.fxgui;

import javafx.beans.property.*;

public class JobSetupView {

    private StringProperty directory = new SimpleStringProperty(this,"directory");
    private IntegerProperty numberOfFiles=new SimpleIntegerProperty(this,"numberOfFiles");
    private BooleanProperty resize = new SimpleBooleanProperty(this,"resize");
    private IntegerProperty resizeWidth = new SimpleIntegerProperty(this,"resizeWidth");


    public String getDirectory() {
        return directory.get();
    }

    public StringProperty directoryProperty() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory.set(directory);
    }

    public int getNumberOfFiles() {
        return numberOfFiles.get();
    }

    public IntegerProperty numberOfFilesProperty() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(int numberOfFiles) {
        this.numberOfFiles.set(numberOfFiles);
    }

    public boolean isResize() {
        return resize.get();
    }

    public BooleanProperty resizeProperty() {
        return resize;
    }

    public void setResize(boolean resize) {
        this.resize.set(resize);
    }

    public int getResizeWidth() {
        return resizeWidth.get();
    }

    public IntegerProperty resizeWidthProperty() {
        return resizeWidth;
    }

    public void setResizeWidth(int resizeWidth) {
        this.resizeWidth.set(resizeWidth);
    }
}
