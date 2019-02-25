package com.stackroute.newsapp.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * This Filter Class is responsible to check all request coming from trusted
 * sources.
 * 
 * @author Ashok
 *
 */
public class JwtFilter extends GenericFilterBean {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		final HttpServletRequest req = (HttpServletRequest) request;
		final HttpServletResponse res = (HttpServletResponse) response;
		final String authHeder = req.getHeader("authorization");

		if ("OPTIONS".equals(req.getMethod())) {
			res.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(request, response);
		} else {
			if (null == authHeder || !authHeder.startsWith("Bearer ")) {
				throw new ServletException("Missing or invalid Authorization header ");
			}
			final String token = authHeder.substring(7);
			final Claims claim = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
			request.setAttribute("claims", claim);
			chain.doFilter(request, response);
		}

	}

}
