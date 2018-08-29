package com.longbridge.sams.security.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class TypeUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	public static final String USER_TYPE = "userType";
	private Logger logger = LoggerFactory.getLogger(this.getClass());

//	@Override
//	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
//			throws AuthenticationException {
//		logger.debug("Attempting authentication");
//		if (!request.getMethod().equals("POST")) {
//			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
//		}
//
//		UsernamePasswordAuthenticationToken authRequest = getAuthRequest(request);
//
//		setDetails(request, authRequest);
//		return this.getAuthenticationManager().authenticate(authRequest);
//	}

//	private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) {
//
//		String username = obtainUsername(request);
//		String password = obtainPassword(request);
//		String userType = obtainUserType(request);
//		logger.debug(
//				String.format("Retrieved Username: %s , Password: %s, usertype: %s", username, password, userType));
//		// ...
//
//		String usernameDomain = String.format("%s%s%s", username.trim(), String.valueOf(Character.LINE_SEPARATOR),
//				userType);
//		return new UsernamePasswordAuthenticationToken(usernameDomain, password);
//	}

//	private String obtainUserType(HttpServletRequest request) {
//		return request.getParameter(USER_TYPE);
//	}

	@Override
	protected String obtainUsername(HttpServletRequest request) {

		String usernameUserType = String.format("%s%s%s", super.obtainUsername(request), String.valueOf(Character.LINE_SEPARATOR),
				request.getParameter(USER_TYPE));
		return usernameUserType ;
	}

}
