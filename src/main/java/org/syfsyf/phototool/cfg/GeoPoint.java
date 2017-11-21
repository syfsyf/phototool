package org.syfsyf.phototool.cfg;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("geopoint")
public class GeoPoint {
	
	private String name;
	private Double lat;
	private Double lng;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	
	

}
