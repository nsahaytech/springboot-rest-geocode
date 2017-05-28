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

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.models.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("retailmgr").apiInfo(apiInfo()).select()
				.paths(regex("/shop.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Shop App")
				.description(
						"Save and fetch Retail manager shop information, which internally consumes google map geocode api")
				.termsOfServiceUrl("https://www.nishantsahay-learn.com/terms-of-use")
				.title("Demo Shop App").version("1.0").build();
	}
	
	private Contact contactInfo(){
		Contact contact = new Contact();
		contact.email("nsahaytech@gmail.com");
		contact.setName("Nishant Sahay");
		contact.setUrl("www.nishantsahay-learn.com");
		return contact;
	}
}
