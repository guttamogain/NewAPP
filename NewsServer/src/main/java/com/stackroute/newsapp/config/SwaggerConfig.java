package com.stackroute.newsapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * This Swagger Class is used for Service Expose
 * 
 * @author ubuntu
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * This Method is responsible for to expose service outside.
	 * 
	 * @return
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.stackroute.newsapp")).build();
		// .paths(PathSelectors.regex("/api/v1/newservice.*")).build();
	}
}
