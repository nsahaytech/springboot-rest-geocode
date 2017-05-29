/**
 * 
 */
package com.ns.retailmgr.shops.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author nishant
 *
 */
public class ShopAddress implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8776195001706328333L;
	
	

	@JsonProperty(value = "shopAddress.number", required = true)
	@ApiModelProperty(notes = "Shop Number", required = true)
	private long shopNumber;
	
	@JsonProperty(value = "shopAddress.postCode", required = true)
	@ApiModelProperty(notes = "Shop location zip code/postal code", required = true)
	private Integer shopPostalCode;
	
	public Integer getShopPostalCode() {
		return shopPostalCode;
	}
	public void setShopPostalCode(Integer shopPostalCode) {
		this.shopPostalCode = shopPostalCode;
	}

	/**
	 * @return the shopNumber
	 */
	public long getShopNumber() {
		return shopNumber;
	}
	/**
	 * @param shopNumber the shopNumber to set
	 */
	public void setShopNumber(long shopNumber) {
		this.shopNumber = shopNumber;
	}

	
}
