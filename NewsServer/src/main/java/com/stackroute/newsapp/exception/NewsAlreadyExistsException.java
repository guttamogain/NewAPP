package com.stackroute.newsapp.exception;

/**
 * This Class is responsible to through NewsAlreadyExistsException.
 * @author Ashok
 *
 */
@SuppressWarnings("serial")
public class NewsAlreadyExistsException extends Exception {

	public NewsAlreadyExistsException(String message){
		super(message);
	}
}
