package com.stackroute.newsapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.stackroute.newsapp.exceptions.UserAlreadyExistsException;
import com.stackroute.newsapp.exceptions.UserNotFoundException;
import com.stackroute.newsapp.model.User;
import com.stackroute.newsapp.services.SecurityTokenGenerator;
import com.stackroute.newsapp.services.UserService;

/**
 * This Controller Class is responsible for register and login Methods for User.
 * 
 * @author ubuntu
 *
 */
@RestController
@EnableWebMvc
@CrossOrigin("*")
@RequestMapping("/api/v1/userservice")

public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	SecurityTokenGenerator securityTokenGenerator;

	/**
	 * This Method is used to register user by itself.
	 * 
	 * @param user
	 * @return String
	 * @throws UserAlreadyExistsException
	 */
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody User user) throws UserAlreadyExistsException {

		try {
			userService.saveUser(user);
			return new ResponseEntity<String>("User registered successfully", HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.CONFLICT);
		}

	}

	/**
	 * This Method is used to login user and store token in local storage.
	 * 
	 * @param loginUser
	 * @return map
	 * @throws UserNotFoundException
	 */
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody User loginUser) throws UserNotFoundException {

		try {

			String username = loginUser.getUsername();
			String password = loginUser.getPassword();

			if (null == username || null == password) {
				throw new Exception("Username or password cannot be empty");
			}
			User user = userService.findByUsernameAndPassword(username, password);

			if (null == user) {
				throw new Exception("User with given username does not exists");
			}

			String passwd = user.getPassword();
			if (!password.equals(passwd)) {
				throw new Exception("Invalid login credential, Please check username and password ");
			}
			Map<String, String> map = securityTokenGenerator.generateToken(user);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("{ \"message\": \"" + e.getMessage() + "\"}", HttpStatus.UNAUTHORIZED);
		}

	}

}
