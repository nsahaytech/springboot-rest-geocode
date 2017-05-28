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
package com.ns.retailmgr.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ns.retailmgr.shops.model.ShopAddress;
import com.ns.retailmgr.shops.model.ShopDetails;
import com.ns.retailmgr.shops.service.ShopService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/shop")
public class ShopController {

		private static final Logger LOGGER = LoggerFactory.getLogger(ShopController.class);

		@Autowired
		@Qualifier("shopService")
		private ShopService shopService;

		@ApiOperation(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "POST", value = "", response = String.class, notes = "Save the shop details")
		@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<Object> saveShop(@RequestBody ShopAddress shopAddress) {
			LOGGER.info("Started endpoint method {}, params - {}", "saveShop");
			try {
				ShopDetails newShopDetails = shopService.addShop(shopAddress);
				if (newShopDetails == null) {
					return new ResponseEntity<Object>(
							"Unable to find latitude and logitude for shop details provided, please check and resubmit again",
							HttpStatus.BAD_REQUEST);
				}
				return new ResponseEntity<Object>(newShopDetails, HttpStatus.CREATED);
			} catch (Exception e) {
				LOGGER.error("Exception {}", e);
				return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			} 
			
		}

		@ApiOperation(consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, httpMethod = "GET", value = "", response = ShopDetails.class, notes = "Find List of shop matching to provided latitude and longitude", responseContainer = "List")
		@ApiImplicitParams({
				@ApiImplicitParam(name = "customerLatitude", value = "Laitude of Customer's location", required = true, dataType = "string", paramType = "query"),
				@ApiImplicitParam(name = "customerLongitude", value = "Longitude of Customer's location", required = true, dataType = "string", paramType = "query") })
		@RequestMapping(method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<?> getNearByShops(
				@RequestParam(required = true, name = "customerLatitude") String customerLatitude,
				@RequestParam(required = true, name = "customerLongitude") String customerLongitude) {
			LOGGER.debug("Started endpoint method {}, params - {}", "getNearByShops",
					new Object[] { customerLatitude, customerLongitude });
			List<ShopDetails> output = null;
			try {
				output = shopService.findShopNearByLatLng(customerLatitude, customerLongitude);
			} catch (Exception e) {
				LOGGER.error("Exception {}", e);
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			} 
			return new ResponseEntity<>(output, HttpStatus.OK);
		}
	}
