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
