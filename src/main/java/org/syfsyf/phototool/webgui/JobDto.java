package org.syfsyf.phototool.webgui;

import org.syfsyf.phototool.cfg.Profile;

public class JobDto extends Profile{
	
	private String directory;	
	private int numberOfFiles;
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public int getNumberOfFiles() {
		return numberOfFiles;
	}
	public void setNumberOfFiles(int numberOfFiles) {
		this.numberOfFiles = numberOfFiles;
	}
	
}
