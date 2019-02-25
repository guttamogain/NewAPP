package com.stackroute.newsapp.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This Class used to Expose services outside.
 * 
 * @author Ashok
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * This Method will help to test services from postman/swagger.
	 * 
	 * @return Docket
	 */
	@Bean
	public Docket ProductApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.stackroute.newsapp")).paths(regex("/user.*")).build();

	}

}
