package com.ns.retailmgr.connector.gmaps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bounds {

	private LatLng northeast;
	private LatLng southwest;
	/**
	 * @return the northeast
	 */
	public LatLng getNortheast() {
		return northeast;
	}
	/**
	 * @param northeast the northeast to set
	 */
	public void setNortheast(LatLng northeast) {
		this.northeast = northeast;
	}
	/**
	 * @return the southwest
	 */
	public LatLng getSouthwest() {
		return southwest;
	}
	/**
	 * @param southwest the southwest to set
	 */
	public void setSouthwest(LatLng southwest) {
		this.southwest = southwest;
	}
	  
}
