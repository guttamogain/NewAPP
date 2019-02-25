package com.stackroute.newsapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stackroute.newsapp.model.User;

/**
 * This Repository Class is used for Repository Operations
 * 
 * @author Ashok
 *
 */
public interface UserRepository extends JpaRepository<User, String> {

	/**
	 * This Method is responsible to verify user.
	 * 
	 * @param username
	 * @param password
	 * @return user
	 */
	User findByUsernameAndPassword(String username, String password);
	

}
