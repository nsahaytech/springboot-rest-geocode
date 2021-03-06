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
package com.ns.retailmgr.shops.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ns.retailmgr.connector.gmaps.GMapConnector;
import com.ns.retailmgr.connector.gmaps.model.AddressComponent;
import com.ns.retailmgr.connector.gmaps.model.GeoCodeLocInfo;
import com.ns.retailmgr.connector.gmaps.model.LngLatInfo;
import com.ns.retailmgr.constant.ShopConstant;
import com.ns.retailmgr.shops.model.ShopAddress;
import com.ns.retailmgr.shops.model.ShopDetails;

@Service("shopService")
public class ShopServiceImpl implements ShopService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShopServiceImpl.class);

	@Autowired
	@Qualifier("gMapConnector")
	private GMapConnector gMapConnector;
	

	private static Map<String, ShopDetails> SHOP_LIST = new ConcurrentHashMap<>();
	
	@Override
	public ShopDetails addShop(ShopDetails shopDetails){
		LOGGER.debug("Started method {} with params - {}", "addShop");
		ShopDetails displayShopDetails = null;
		ShopAddress shopAddress = shopDetails.getShopAddress();
		String shopName = shopDetails.getShopName();
		ShopDetails oldShopDetails = SHOP_LIST.get(shopName);
		final Map<String, String> latLngMap = gMapConnector.getLngLatByAddress(shopAddress.getShopPostalCode().toString());
		if (!latLngMap.isEmpty()) {
			displayShopDetails = new ShopDetails();
			displayShopDetails.setShopName(shopName);
			displayShopDetails.setShopAddress(shopAddress);
			displayShopDetails.setShopLatitude(latLngMap.get(ShopConstant.LATITUDE_KEY));
			displayShopDetails.setShopLongtitude(latLngMap.get(ShopConstant.LONGITUDE_KEY));
			
			SHOP_LIST.put(shopName, displayShopDetails);
			if(oldShopDetails != null){
				return oldShopDetails;
			}else{
				displayShopDetails.setStatus("NEW");
				return displayShopDetails;
			}
			
			
		}
		return displayShopDetails;
	}

	@Override
	public List<ShopDetails> findShopNearByLatLng(String custLat, String custLng) {
		LOGGER.debug("Started method {} with params - {}", "findShopNearByLatLng", new Object[] { custLat, custLng });
		final String latlngParam = custLat + "," + custLng;
		final GeoCodeLocInfo codingResponse = gMapConnector.getNearestShopDetails(latlngParam);
		LngLatInfo[] results = codingResponse.getResults();
		final List<ShopDetails> output = new ArrayList<>();
		final Map<String, ShopDetails> tempMap = new HashMap<>();
		for (int i = 0; i < results.length; i++) {
			LngLatInfo result = results[i];
			AddressComponent[] addressComponents = result.getAddressComponents();
			for (int j = 0; j < addressComponents.length; j++) {
				AddressComponent addressComponent = addressComponents[j];
				addressComponent.getTypes();
				getResult(tempMap, addressComponent);
			}
		}
		output.addAll(tempMap.values());
		return output;
	}

	private void getResult(final Map<String, ShopDetails> tempMap, AddressComponent addressComponent) {
		for (String types : addressComponent.getTypes()) {
			if (types.equalsIgnoreCase("postal_code")) {
				for (Map.Entry<String, ShopDetails> entry : SHOP_LIST.entrySet()) {
					ShopDetails shopDetailsVo = entry.getValue();
					ShopAddress tempShopAddress = shopDetailsVo.getShopAddress();
					String pCode = Integer.toString(tempShopAddress.getShopPostalCode());
					if (addressComponent.getLongName().equalsIgnoreCase(pCode)
							|| addressComponent.getShortName().equalsIgnoreCase(pCode)) {
						tempMap.put(shopDetailsVo.getShopName(), shopDetailsVo);
					}
				}
			}
		}
	}
	

}
