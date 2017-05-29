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
package com.ns.retailmgr.controller;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ns.retailmgr.shops.model.ShopAddress;
import com.ns.retailmgr.shops.model.ShopDetails;
import com.ns.retailmgr.shops.service.ShopService;

import net.minidev.json.JSONObject;

/**
 * @author nishant
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ShopController.class)
public class ShopControllerTest {
	
    @Autowired
    MockMvc mockMvc;
    
    @MockBean
    ShopService shopService;
    
    
    @Autowired
    ObjectMapper objectMapper;
    
    private JSONObject jsonAddress;
    
    private ShopDetails mockShopDetails;
    
    private ShopAddress mockShopAddress;
    
    
    @Before
	public void setUp() throws Exception {
    	
    	jsonAddress = new JSONObject();
    	jsonAddress.put("shopAddress.number", 1234);
    	jsonAddress.put("shopAddress.postCode", 560099);
    	jsonAddress.put("shopName", "testAddShop_Success");
    	
    	mockShopAddress = new ShopAddress();
    	mockShopAddress.setShopNumber(1234);
    	mockShopAddress.setShopPostalCode(560099);
    	    	
    	mockShopDetails = new ShopDetails();
    	mockShopDetails.setShopName("testAddShop_Success");
    	mockShopDetails.setShopAddress(mockShopAddress);
    	
    	
    	
    }
    
    @Test
    public void test_addShop_Success() throws Exception {
    	mockShopDetails.setStatus("New");
    	when(shopService.addShop(any(ShopDetails.class))).thenReturn(mockShopDetails);
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/shop").accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(jsonAddress))
				.contentType(MediaType.APPLICATION_JSON);

    	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
    
    @Test
    public void test_getNearByShops_Success() throws Exception {
    	List<ShopDetails> mockShopList = new ArrayList<ShopDetails>();
    	mockShopList.add(mockShopDetails);
    	
    	when(shopService.findShopNearByLatLng(anyString(), anyString())).thenReturn(mockShopList);
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/shop").param("customerLatitude", "00")
                .param("customerLongitude", "00").accept(MediaType.APPLICATION_JSON);

    	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    	MockHttpServletResponse response = result.getResponse();
    	String responseString = response.getContentAsString();
    	org.json.JSONArray myObject = new org.json.JSONArray(responseString);
		
		assertEquals(1, myObject.length());
    }
    
    @Test
    public void test_updateShop_Success() throws Exception {
    	
    	when(shopService.addShop(any(ShopDetails.class))).thenReturn(mockShopDetails);
    	RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
				"/shop").accept(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsBytes(jsonAddress))
				.contentType(MediaType.APPLICATION_JSON);

    	MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

}
