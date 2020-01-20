package org.syfsyf.phototool.domain;

import org.syfsyf.phototool.cfg.GeoPoint;
import org.syfsyf.phototool.cfg.Profile;

import java.util.ArrayList;
import java.util.List;

public class JobSetupDto extends Profile {

    private String directory;
    private int numberOfFiles;

    private List<GeoPoint> geoPoints = new ArrayList<>();

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
