package com.stackroute.newsapp.services;

import java.util.Map;

import com.stackroute.newsapp.model.User;

/**
 * This Class have Token Generator methods.
 * 
 * @author Ashok
 *
 */
public interface SecurityTokenGenerator {

	/**
	 * This Method is used to generate JWT Token for logied in user.
	 * 
	 * @param user
	 * @return map
	 */
	Map<String, String> generateToken(User user);
}
