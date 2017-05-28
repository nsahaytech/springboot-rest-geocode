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
package com.ns.retailmgr.shops.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class ShopDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2338801555716686229L;
	

	
	@JsonProperty(value = "address", required = true)
	@ApiModelProperty(notes = "The Shop Address", required = true)
	private ShopAddress shopAddress;
	
	private String shopLongtitude;
	private String shopLatitude;
	
	public ShopAddress getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(ShopAddress shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopLongtitude() {
		return shopLongtitude;
	}
	public void setShopLongtitude(String shopLongtitude) {
		this.shopLongtitude = shopLongtitude;
	}
	public String getShopLatitude() {
		return shopLatitude;
	}
	public void setShopLatitude(String shopLatitude) {
		this.shopLatitude = shopLatitude;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
