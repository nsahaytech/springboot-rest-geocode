package com.ns.retailmgr.shops.service;

import java.util.List;

import com.ns.retailmgr.shops.model.ShopDetails;

public interface ShopService {
	
	public int addShop(final ShopDetails shopDetails) throws Exception;
	
	public List<ShopDetails> findShopNearByLatLng(final String custLat, final String custLng) throws Exception;

}
