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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LngLatInfo {
	@JsonProperty("address_components")
	private AddressComponent[] addressComponents;

	@JsonProperty("formatted_address")
	private String formattedAddress;

	@JsonIgnore
	private String[] postcodeLocalities;

	@JsonProperty("geometry")
	private Geometry geometry;

	@JsonProperty("types")
	private String[] types;

	private boolean partialMatch;

	@JsonProperty("place_id")
	private String placeId;

	/**
	 * @return the addressComponents
	 */
	public AddressComponent[] getAddressComponents() {
		return addressComponents;
	}

	/**
	 * @param addressComponents the addressComponents to set
	 */
	public void setAddressComponents(AddressComponent[] addressComponents) {
		this.addressComponents = addressComponents;
	}

	/**
	 * @return the formattedAddress
	 */
	public String getFormattedAddress() {
		return formattedAddress;
	}

	/**
	 * @param formattedAddress the formattedAddress to set
	 */
	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

	/**
	 * @return the postcodeLocalities
	 */
	public String[] getPostcodeLocalities() {
		return postcodeLocalities;
	}

	/**
	 * @param postcodeLocalities the postcodeLocalities to set
	 */
	public void setPostcodeLocalities(String[] postcodeLocalities) {
		this.postcodeLocalities = postcodeLocalities;
	}

	/**
	 * @return the geometry
	 */
	public Geometry getGeometry() {
		return geometry;
	}

	/**
	 * @param geometry the geometry to set
	 */
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}

	/**
	 * @return the types
	 */
	public String[] getTypes() {
		return types;
	}

	/**
	 * @param types the types to set
	 */
	public void setTypes(String[] types) {
		this.types = types;
	}

	/**
	 * @return the partialMatch
	 */
	public boolean isPartialMatch() {
		return partialMatch;
	}

	/**
	 * @param partialMatch the partialMatch to set
	 */
	public void setPartialMatch(boolean partialMatch) {
		this.partialMatch = partialMatch;
	}

	/**
	 * @return the placeId
	 */
	public String getPlaceId() {
		return placeId;
	}

	/**
	 * @param placeId the placeId to set
	 */
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}
	
}
