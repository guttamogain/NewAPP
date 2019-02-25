package com.stackroute.newsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Ashok
 * 
 *         This Class will start Spring Boot Application by creating Beans
 *
 */
@SpringBootApplication(scanBasePackages = { "com.stackroute.newsapp" })
public class NewsAppApplication {

	/**
	 * This Method will start News Server Application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(NewsAppApplication.class, args);
		System.out.println("Welcome to News Application Login Service");
	}
}
