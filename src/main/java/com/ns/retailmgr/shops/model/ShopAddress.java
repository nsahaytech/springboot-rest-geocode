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
	
	@JsonProperty(value = "shopName", required = true)
    @ApiModelProperty(notes = "The name of the shop", required = true)
	private String shopName;

	@JsonProperty(value = "shopAddress.number", required = true)
	@ApiModelProperty(notes = "Shop Number", required = true)
	private long shopNumber;
//	
//	@JsonProperty(value = "shopAddressLine1", required = true)
//	@ApiModelProperty(notes = "Shop Address Line 1", required = true)
//	private long shopAddressLine1;
//	
//	@JsonProperty(value = "shopAddressLine2", required = false)
//	@ApiModelProperty(notes = "Shop Address Line 2", required = false)
//	private long shopAddressLine2;
	
	@JsonProperty(value = "shopAddress.postCode", required = true)
	@ApiModelProperty(notes = "Shop location zip code/postal code", required = true)
	private Integer shopPostalCode;
	
	public Integer getShopPostalCode() {
		return shopPostalCode;
	}
	public void setShopPostalCode(Integer shopPostalCode) {
		this.shopPostalCode = shopPostalCode;
	}
	
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
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
//	/**
//	 * @return the shopAddressLine1
//	 */
//	public long getShopAddressLine1() {
//		return shopAddressLine1;
//	}
//	/**
//	 * @param shopAddressLine1 the shopAddressLine1 to set
//	 */
//	public void setShopAddressLine1(long shopAddressLine1) {
//		this.shopAddressLine1 = shopAddressLine1;
//	}
//	/**
//	 * @return the shopAddressLine2
//	 */
//	public long getShopAddressLine2() {
//		return shopAddressLine2;
//	}
//	/**
//	 * @param shopAddressLine2 the shopAddressLine2 to set
//	 */
//	public void setShopAddressLine2(long shopAddressLine2) {
//		this.shopAddressLine2 = shopAddressLine2;
//	}

	
}
