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
