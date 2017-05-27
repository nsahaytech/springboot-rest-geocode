package com.ns.retailmgr.shops.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class ShopDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2338801555716686229L;
	
	@JsonProperty(value = "shopName", required = true)
    @ApiModelProperty(notes = "The name of the shop", required = true)
	private String shopName;
	
	@JsonProperty(value = "address", required = true)
	@ApiModelProperty(notes = "The Shop Address Number to be specified", required = true)
	private long shopAddress;
	
	@JsonProperty(value = "postCode", required = true)
	@ApiModelProperty(notes = "Shop location zip code/postal code", required = true)
	private Integer shopPostalCode;
	
	private String shopLongtitude;
	private String shopLatitude;
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Long getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(Long shopAddress) {
		this.shopAddress = shopAddress;
	}
	public Integer getShopPostalCode() {
		return shopPostalCode;
	}
	public void setShopPostalCode(Integer shopPostalCode) {
		this.shopPostalCode = shopPostalCode;
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