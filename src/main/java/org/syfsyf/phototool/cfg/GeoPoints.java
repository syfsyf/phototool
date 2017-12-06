package org.syfsyf.phototool.cfg;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("geopoints")
public class GeoPoints {

	@XStreamImplicit
	List<GeoPoint> geoPoints = new ArrayList<>();

	public List<GeoPoint> getGeoPoints() {
		return geoPoints;
	}

	public void setGeoPoints(List<GeoPoint> geoPoints) {
		this.geoPoints = geoPoints;
	}

}
