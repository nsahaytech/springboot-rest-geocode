package com.ns.retailmgr.shops.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import com.ns.retailmgr.shops.dao.ShopDao;
import com.ns.retailmgr.shops.model.ShopDetails;

@Service("shopService")
public class ShopServiceImpl implements ShopService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ShopServiceImpl.class);

	@Autowired
	@Qualifier("shopDao")
	private ShopDao shopDao;

	@Autowired
	@Qualifier("gMapConnector")
	private GMapConnector gMapConnector;
	

	private static final List<ShopDetails> SHOP_LIST = new CopyOnWriteArrayList<>();
	
	@Override
	public int addShop(ShopDetails shopDetails) throws Exception {
		LOGGER.debug("Started method {} with params - {}", "saveShopDetails", shopDetails);
		
		final Map<String, String> latLngMap = gMapConnector.getLngLatByAddress(shopDetails.getShopAddress());
		if (!latLngMap.isEmpty()) {
			shopDetails.setShopLatitude(latLngMap.get(ShopConstant.LATITUDE_KEY));
			shopDetails.setShopLongtitude(latLngMap.get(ShopConstant.LONGITUDE_KEY));
			SHOP_LIST.add(shopDetails);
			return shopDao.saveShopDetails(shopDetails);
		}
		return 0;
	}

	@Override
	public List<ShopDetails> findShopNearByLatLng(String custLat, String custLng) throws Exception {
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
				for (ShopDetails shopDetailsVo : SHOP_LIST) {
					String pCode = Integer.toString(shopDetailsVo.getShopPostalCode());
					if (addressComponent.getLongName().equalsIgnoreCase(pCode)
							|| addressComponent.getShortName().equalsIgnoreCase(pCode)) {
						tempMap.put(shopDetailsVo.getShopName(), shopDetailsVo);
					}
				}
			}
		}
	}
	

}
