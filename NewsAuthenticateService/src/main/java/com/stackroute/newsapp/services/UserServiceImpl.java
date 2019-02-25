package com.stackroute.newsapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.newsapp.exceptions.UserAlreadyExistsException;
import com.stackroute.newsapp.exceptions.UserNotFoundException;
import com.stackroute.newsapp.model.User;
import com.stackroute.newsapp.repositories.UserRepository;

/**
 * This Service Class is used for User Operations.
 * 
 * @author Ashok
 *
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean saveUser(User user) throws UserAlreadyExistsException {
		// TODO Auto-generated method stub
		Optional<User> existingUser = userRepository.findById(user.getUsername());
		if (existingUser.isPresent()) {
			throw new UserAlreadyExistsException("username Already Exists. Please try someother");
		}

		userRepository.save(user);

		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsernameAndPassword(username, password);

		if (user == null) {
			throw new UserNotFoundException("UserId and Password mismatch");
		}
		return user;
	}

}
