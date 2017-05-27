package com.ns.retailmgr.connector.gmaps.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoCodeLocInfo {

	@JsonProperty("results")
	private LngLatInfo[] results;
	
	@JsonProperty("status")
	private String status;

	/**
	 * @return the results
	 */
	public LngLatInfo[] getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(LngLatInfo[] results) {
		this.results = results;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	

}
