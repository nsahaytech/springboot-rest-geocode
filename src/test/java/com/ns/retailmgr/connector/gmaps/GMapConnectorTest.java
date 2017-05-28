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
package com.ns.retailmgr.connector.gmaps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.ns.retailmgr.connector.gmaps.GMapConnector;
import com.ns.retailmgr.connector.gmaps.GMapConnectorImpl;
import com.ns.retailmgr.connector.gmaps.model.AddressComponent;
import com.ns.retailmgr.connector.gmaps.model.GeoCodeLocInfo;
import com.ns.retailmgr.connector.gmaps.model.Geometry;
import com.ns.retailmgr.connector.gmaps.model.LatLng;
import com.ns.retailmgr.connector.gmaps.model.LngLatInfo;

/**
 * @author nishant
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class GMapConnectorTest {
	
	@InjectMocks
	private GMapConnector gmapConnector = new GMapConnectorImpl();

	@Mock
	private RestTemplate restTemplate;

	@Before
	public void setUp() throws Exception {
		ReflectionTestUtils.setField(gmapConnector, "geoLatngUrl", "https://test.com");
		ReflectionTestUtils.setField(gmapConnector, "geoLocationUrl", "https://test.com");
	}

	@Test
	public void test_getLngLatByAddress_Success() {
		GeoCodeLocInfo codingResponse = testGeoCodingResponse();
		when(restTemplate.getForObject(anyString(), eq(GeoCodeLocInfo.class))).thenReturn(codingResponse);
		Map<String, String> map = gmapConnector.getLngLatByAddress("TestShop");
		assertEquals(map.get("lat"), Double.toString(12.8124));
		assertEquals(map.get("lng"), Double.toString(177.69));
	}

	@Test(expected = RuntimeException.class)
	public void test_getLngLatByAddress_Failure() {
		when(restTemplate.getForObject(anyString(), eq(GeoCodeLocInfo.class)))
				.thenThrow(new HttpClientErrorException(HttpStatus.BAD_REQUEST));
		gmapConnector.getLngLatByAddress("TestShop");
	}

	@Test
	public void test_getNearestShopDetails_Success() {
		GeoCodeLocInfo codingResponse = testGeoCodingResponse();
		when(restTemplate.getForObject(anyString(), eq(GeoCodeLocInfo.class))).thenReturn(codingResponse);
		GeoCodeLocInfo response = gmapConnector.getNearestShopDetails("12.8124,77.69");
		assertNotNull(response);
	}

	@Test(expected = RuntimeException.class)
	public void test_getNearestShopDetails_Failure() {
		when(restTemplate.getForObject(anyString(), eq(GeoCodeLocInfo.class)))
				.thenThrow(new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR));
		gmapConnector.getNearestShopDetails("12.8124,77.69");
	}

	private GeoCodeLocInfo testGeoCodingResponse() {
		GeoCodeLocInfo geoCodingResponse = new GeoCodeLocInfo();
		AddressComponent addressComponent = new AddressComponent();
		LngLatInfo result = new LngLatInfo();
		Geometry geometry = new Geometry();
		LatLng latLng = new LatLng();
		latLng.setLat(12.8124);
		latLng.setLng(177.69);
		geometry.setLocation(latLng);
		String[] types = { "type1", "type2" };
		addressComponent.setLongName("LongName");
		addressComponent.setShortName("shortName");
		addressComponent.setTypes(types);
		AddressComponent addressComponents[] = { addressComponent };
		result.setAddressComponents(addressComponents);
		result.setFormattedAddress("address");
		result.setGeometry(geometry);
		result.setTypes(types);
		result.setPartialMatch(true);
		LngLatInfo[] resultsArr = { result };
		geoCodingResponse.setResults(resultsArr);
		geoCodingResponse.setStatus("testStatus");
		return geoCodingResponse;
	}
}
