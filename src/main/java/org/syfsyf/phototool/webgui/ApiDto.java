package org.syfsyf.phototool.webgui;

import java.util.ArrayList;
import java.util.List;

import org.syfsyf.phototool.cfg.GeoPoint;
import org.syfsyf.phototool.cfg.Profile;

public class ApiDto extends Profile{
	
	private String directory;	
	private int numberOfFiles;
	
	List<GeoPoint> geoPoints=new ArrayList<>();
	
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
	public List<GeoPoint> getGeoPoints() {
		return geoPoints;
	}
	public void setGeoPoints(List<GeoPoint> geoPoints) {
		this.geoPoints = geoPoints;
	}
	
}
