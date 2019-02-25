package com.stackroute.newsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.stackroute.newsapp.filter.JwtFilter;

/**
 * This Class will start Spring Boot Application.
 * 
 * @author Ashok
 *
 */
@SpringBootApplication(scanBasePackages = { "com.stackroute.newsapp" })
public class NewsAppApplication {

	/**
	 * This Method will check for the token, weather user is verified or not.
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean jwtFilter() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}

	/**
	 * This Method is used to start Application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(NewsAppApplication.class, args);
		System.out.println("Welcome to News Application Server Service");
	}

}
