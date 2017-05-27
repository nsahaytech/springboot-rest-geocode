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
		return new Docket(DocumentationType.SWAGGER_2).groupName("testswager").apiInfo(apiInfo()).select()
				.paths(regex("/shop.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Shop App")
				.description(
						"Save and fetch Retail manager shop information, which internally consumer google map services api")
				.termsOfServiceUrl("https://www.dummy.com/terms-of-use")
				.title("Demo Shop App").version("1.0").build();
	}
	
	private Contact contactInfo(){
		Contact contact = new Contact();
		contact.email("nsahaytech@gmail.com");
		contact.setName("Nishant Sahay");
		contact.setUrl("www.nishantsahay.learn.com");
		return contact;
	}
}
