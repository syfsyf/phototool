package org.syfsyf.phototool.gui;

import java.awt.Color;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ViewModel {

	private boolean addSignature = true;
	private boolean autolevel = false;
	private boolean border = false;
	private Color borderColor = Color.black;
	private int borderSize;
	private String dirLabel;
	private String errorLabel;
	private String numOfFiles;
	private String outDirLabel = "test";
	private String progressLabel;
	private int progressValue;
	private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private boolean resize = true;
	private int resizeWidth = 1300;
	private String sigFile;
	private String sigGeometry = "+20+20";
	private String sigGravity = "SouthWest";
	private String sigResize = "x10";
	private String customParams="";

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public Color getBorderColor() {
		return this.borderColor;
	}

	public int getBorderSize() {
		return this.borderSize;
	}

	public String getDirLabel() {
		return this.dirLabel;
	}

	public String getErrorLabel() {
		return this.errorLabel;
	}

	public String getNumOfFiles() {
		return this.numOfFiles;
	}

	public String getOutDirLabel() {
		return outDirLabel;
	}

	public String getProgressLabel() {
		return this.progressLabel;
	}

	public int getProgressValue() {
		return this.progressValue;
	}

	public int getResizeWidth() {
		return this.resizeWidth;
	}

	public String getSigFile() {
		return sigFile;
	}

	public String getSigGeometry() {
		return sigGeometry;
	}

	public String getSigGravity() {
		return sigGravity;
	}

	public String getSigResize() {
		return sigResize;
	}

	public boolean isAddSignature() {
		return addSignature;
	}

	public boolean isAutolevel() {
		return this.autolevel;
	}

	public boolean isBorder() {
		return this.border;
	}

	public boolean isResize() {
		return this.resize;
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
	}

	public void setAddSignature(boolean addSignature) {
		this.addSignature = addSignature;
	}

	public void setAutolevel(boolean autolevel) {
		boolean autolevelOld = this.autolevel;
		this.autolevel = autolevel;
		propertyChangeSupport.firePropertyChange("autolevel", autolevelOld, autolevel);
	}

	public void setBorder(boolean border) {
		boolean borderOld = this.border;
		this.border = border;
		propertyChangeSupport.firePropertyChange("border", borderOld, border);
	}

	public void setBorderColor(Color borderColor) {
		Color borderColorOld = this.borderColor;
		this.borderColor = borderColor;
		propertyChangeSupport.firePropertyChange("borderColor", borderColorOld, borderColor);
	}

	public void setBorderSize(int borderSize) {
		int borderSizeOld = this.borderSize;
		this.borderSize = borderSize;
		propertyChangeSupport.firePropertyChange("borderSize", borderSizeOld, borderSize);
	}

	public void setDirLabel(String dirLabel) {
		String dirLabelOld = this.dirLabel;
		this.dirLabel = dirLabel;
		propertyChangeSupport.firePropertyChange("dirLabel", dirLabelOld, dirLabel);
	}

	public void setErrorLabel(String errorLabel) {
		String errorLabelOld = this.errorLabel;
		this.errorLabel = errorLabel;
		propertyChangeSupport.firePropertyChange("errorLabel", errorLabelOld, errorLabel);
	}

	public void setNumOfFiles(String numOfFiles) {
		String numOfFilesOld = this.numOfFiles;
		this.numOfFiles = numOfFiles;
		propertyChangeSupport.firePropertyChange("numOfFiles", numOfFilesOld, numOfFiles);
	}

	public void setOutDirLabel(String outDirLabel) {
		String outDirLabelOld = this.outDirLabel;
		this.outDirLabel = outDirLabel;
		propertyChangeSupport.firePropertyChange("outDirLabel", outDirLabelOld, outDirLabel);
	}

	public void setProgressLabel(String progressLabel) {
		String progressLabelOld = this.progressLabel;
		this.progressLabel = progressLabel;
		propertyChangeSupport.firePropertyChange("progressLabel", progressLabelOld, progressLabel);
	}

	public void setProgressValue(int progressValue) {
		int progressValueOld = this.progressValue;
		this.progressValue = progressValue;
		propertyChangeSupport.firePropertyChange("progressValue", progressValueOld, progressValue);
	}

	public void setResize(boolean resize) {
		boolean resizeOld = this.resize;
		this.resize = resize;
		propertyChangeSupport.firePropertyChange("resize", resizeOld, resize);
	}

	public void setResizeWidth(int resizeWidth) {
		int resizeWidthOld = this.resizeWidth;
		this.resizeWidth = resizeWidth;
		propertyChangeSupport.firePropertyChange("resizeWidth", resizeWidthOld, resizeWidth);
	}

	public void setSigFile(String sigFile) {
		this.sigFile = sigFile;
	}

	public void setSigGeometry(String sigGeometry) {
		this.sigGeometry = sigGeometry;
	}

	public void setSigGravity(String sigGravity) {
		this.sigGravity = sigGravity;
	}

	public void setSigResize(String sigResize) {
		this.sigResize = sigResize;
	}

	public String getCustomParams() {
		return customParams;
	}

	public void setCustomParams(String customParams) {
		this.customParams = customParams;
	}
	

}
