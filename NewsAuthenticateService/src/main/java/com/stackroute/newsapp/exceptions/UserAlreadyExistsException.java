package com.stackroute.newsapp.exceptions;

/**
 * This class is used to Exception handling
 * 
 * @author Ashok
 *
 */
@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Throwable {

	/**
	 * This Method responsible to through UserAlreadyExistsException
	 * 
	 * @param message
	 */
	public UserAlreadyExistsException(String message) {
		super(message);

	}
}
