package com.stackroute.newsapp.services;

import com.stackroute.newsapp.exceptions.UserAlreadyExistsException;
import com.stackroute.newsapp.exceptions.UserNotFoundException;
import com.stackroute.newsapp.model.User;

/**
 * This Interface is used for the user Operations
 * 
 * @author Ashok
 *
 */
public interface UserService {

	/**
	 * This Method is responsible to save User.
	 * 
	 * @param user
	 * @return boolean
	 * @throws UserAlreadyExistsException
	 */
	boolean saveUser(User user) throws UserAlreadyExistsException;

	/**
	 * This Method is responsible to verify User.
	 * 
	 * @param username
	 * @param password
	 * @return User
	 * @throws UserNotFoundException
	 */
	User findByUsernameAndPassword(String username, String password) throws UserNotFoundException;
}
