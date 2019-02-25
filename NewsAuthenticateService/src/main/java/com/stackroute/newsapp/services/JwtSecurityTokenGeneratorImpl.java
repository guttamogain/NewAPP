package com.stackroute.newsapp.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.stackroute.newsapp.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * This Class is used to Security Token Operations.
 * 
 * @author Ashok
 *
 */
@Service
public class JwtSecurityTokenGeneratorImpl implements SecurityTokenGenerator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<String, String> generateToken(User user) {
		Map<String, String> map = new HashMap<>();
		String jwtToken = "";
		jwtToken = Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
		System.out.println(jwtToken);
		map.put("token", jwtToken);
		map.put("username", user.getUsername());
		map.put("message", "user loggedin sucessfully...");

		return map;
	}

}
