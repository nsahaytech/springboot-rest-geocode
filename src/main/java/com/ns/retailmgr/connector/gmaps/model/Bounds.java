/*******************************************************************************
 * Copyright 2017 nishant
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
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
