package com.ns.retailmgr.shops.dao;

import com.ns.retailmgr.shops.model.ShopDetails;

public interface ShopDao {

	int saveShopDetails(final ShopDetails shopDetails) throws Exception;
}
