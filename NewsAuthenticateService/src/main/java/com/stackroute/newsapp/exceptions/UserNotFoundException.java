package com.stackroute.newsapp.exceptions;

/**
 * This class is used to Exception handling
 * 
 * @author Ashok
 *
 */
@SuppressWarnings("serial")
public class UserNotFoundException extends Throwable {

	/**
	 * This Method is use to through UserNotFoundException
	 * 
	 * @param message
	 */
	public UserNotFoundException(String message) {
		super(message);
	}
}
