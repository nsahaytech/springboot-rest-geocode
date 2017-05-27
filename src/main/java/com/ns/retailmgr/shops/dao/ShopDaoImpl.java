package com.ns.retailmgr.shops.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ns.retailmgr.shops.model.ShopDetails;

@Repository("shopDao")
public class ShopDaoImpl implements ShopDao{

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopDaoImpl.class);
	public static final String SAVE_SHOP_DETAILS = "insert into shop_details values(?, ?, ?, ?, ?)";

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public int saveShopDetails(final ShopDetails shopDetails) throws Exception {
		LOGGER.debug("Starting method {} with params - {} ", "saveShopDetails", shopDetails.toString());
		Object[] params = { shopDetails.getShopName(), shopDetails.getShopAddress(), shopDetails.getShopPostalCode(),
				shopDetails.getShopLatitude(), shopDetails.getShopLongtitude() };
		try{
			return jdbcTemplate.update(SAVE_SHOP_DETAILS, params);
		} catch (RuntimeException e){
			throw new RuntimeException("Unable to add Shop address at this moment! Please check request or try later.", e.getCause());
		}
	}

}
