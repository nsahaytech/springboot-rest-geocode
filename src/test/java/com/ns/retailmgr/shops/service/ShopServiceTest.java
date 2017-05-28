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
/**
 * 
 */
package com.ns.retailmgr.shops.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.ns.retailmgr.connector.gmaps.GMapConnector;
import com.ns.retailmgr.connector.gmaps.model.AddressComponent;
import com.ns.retailmgr.connector.gmaps.model.GeoCodeLocInfo;
import com.ns.retailmgr.connector.gmaps.model.Geometry;
import com.ns.retailmgr.connector.gmaps.model.LatLng;
import com.ns.retailmgr.connector.gmaps.model.LngLatInfo;
import com.ns.retailmgr.shops.model.ShopAddress;
import com.ns.retailmgr.shops.model.ShopDetails;

/**
 * @author nishant
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class ShopServiceTest {
	@InjectMocks
	private ShopService shopDetailsService = new ShopServiceImpl();

	@Mock
	private GMapConnector gmapConnector;

	
	private ShopDetails shopDetails;
	
	private ShopAddress address;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	
		shopDetails = new ShopDetails();

		address = new ShopAddress();
		address.setShopName("Test Shop");
		address.setShopNumber(123);
		address.setShopPostalCode(560099);
		shopDetails.setShopAddress(address);
	}

	@Test
	public void test_saveShopDetails_success() throws Exception {
		Map<String, String> latlngMap = new HashMap<>();
		latlngMap.put("lat", "12.8124199");
		latlngMap.put("lng", "77.69403150000001");
		when(gmapConnector.getLngLatByAddress(anyString())).thenReturn(latlngMap);
		ShopDetails addShopDetails = shopDetailsService.addShop(address);
		assertEquals(shopDetails.getShopAddress().getShopName(), addShopDetails.getShopAddress().getShopName());
	}

	@Test(expected = Exception.class)
	public void test_saveShopDetails_failure() throws Exception {
		Map<String, String> latlngMap = new HashMap<>();
		latlngMap.put("lat", "12.8124199");
		latlngMap.put("lng", "77.69403150000001");
		when(gmapConnector.getLngLatByAddress(anyString())).thenThrow(new Exception());
		shopDetailsService.addShop(address);
	}
	
	@Test
	public void testFindShopNearByLatLng_success() throws Exception {
		when(gmapConnector.getNearestShopDetails(anyString())).thenReturn(testGeoCodeResponse());
		List<ShopDetails> actual = shopDetailsService.findShopNearByLatLng("12.8124", "77.69");
		assertNotNull(actual);
	}

	private GeoCodeLocInfo testGeoCodeResponse() {
		GeoCodeLocInfo geoCodeLocInfo = new GeoCodeLocInfo();
		AddressComponent addressComponent = new AddressComponent();
		LngLatInfo result = new LngLatInfo();
		Geometry geometry = new Geometry();
		LatLng latLng = new LatLng();
		latLng.setLat(12.8124);
		latLng.setLng(77.69);
		geometry.setLocation(latLng);
		String [] types= {"type1","type2"};
		addressComponent.setLongName("LongName");
		addressComponent.setShortName("shortName");
		addressComponent.setTypes(types);
		AddressComponent addressComponents[]= {addressComponent};
		result.setAddressComponents(addressComponents);
		result.setFormattedAddress("address");
		result.setGeometry(geometry);
		result.setTypes(types);
		result.setPartialMatch(true);
		LngLatInfo[] resultsArr = {result};
		geoCodeLocInfo.setResults(resultsArr);
		geoCodeLocInfo.setStatus("testStatus");
		return geoCodeLocInfo;
	}
	
	

}
