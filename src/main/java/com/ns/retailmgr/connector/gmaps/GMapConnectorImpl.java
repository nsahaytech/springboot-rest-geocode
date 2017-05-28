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
package com.ns.retailmgr.connector.gmaps;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.ns.retailmgr.connector.gmaps.model.GeoCodeLocInfo;
import com.ns.retailmgr.connector.gmaps.model.LngLatInfo;
import com.ns.retailmgr.constant.ShopConstant;

@Service("gMapConnector")
public class GMapConnectorImpl implements GMapConnector {

	private static final Logger LOGGER = LoggerFactory.getLogger(GMapConnectorImpl.class);

	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;

	@Value("${gmaps.lnglat.url}")
	private String geoLatngUrl;

	@Value("${gmaps.location.url}")
	private String geoLocationUrl;
	
	@Override
	public Map<String, String> getLngLatByAddress(Long address) throws RuntimeException {
		LOGGER.debug("Started method {} - params {}", "getLngLatByAddress", address);
		final String url = geoLatngUrl + address;
		try {
			final GeoCodeLocInfo geocodingResult = restTemplate.getForObject(url, GeoCodeLocInfo.class);
			final Map<String, String> returnMap = new HashMap<>();
			LngLatInfo[] results = geocodingResult.getResults();
			for (LngLatInfo res : results) {
				returnMap.put(ShopConstant.LATITUDE_KEY, Double.toString(res.getGeometry().getLocation().getLat()));
				returnMap.put(ShopConstant.LONGITUDE_KEY, Double.toString(res.getGeometry().getLocation().getLng()));
			}
			return returnMap;
		} catch (RestClientException e) {
			LOGGER.error("Failed to find location lng lat. Exception {}", e);
			throw new RuntimeException("Failed to find latitude/longitude of address", e.getCause());
		}
	}

	@Override
	public GeoCodeLocInfo getNearestShopDetails(String latlngParam) throws RuntimeException {
		LOGGER.info("Started method {} - params {}", "getNearestShopDetails", latlngParam);
		final String url = geoLocationUrl + latlngParam;
		try {
			return restTemplate.getForObject(url, GeoCodeLocInfo.class);
		} catch (RestClientException e) {
			LOGGER.error("Failed to find location lng lat. Exception {}", e);
			throw new RuntimeException("Failed to find nearest locations of latitude/longitude", e.getCause());
		}
	}

}
