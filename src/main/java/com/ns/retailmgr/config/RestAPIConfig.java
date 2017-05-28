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
package com.ns.retailmgr.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
@PropertySource(value = {"classpath:gmaps-config.properties"})
public class RestAPIConfig {
	
	@Bean(name = "restTemplate")
	public RestTemplate restTemplate(@Value("${gmaps.api.read.timeout}") int readTimeOut, @Value("${gmaps.api.connect.timeout}") int connectTimeOut){
		return new RestTemplate(clientHttpRequestFactory(readTimeOut, connectTimeOut));
	}
	
	private ClientHttpRequestFactory clientHttpRequestFactory(int readTimeOut,int connectTimeOut) {
        final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(readTimeOut);
        factory.setConnectTimeout(connectTimeOut);
        return factory;
	}
}
