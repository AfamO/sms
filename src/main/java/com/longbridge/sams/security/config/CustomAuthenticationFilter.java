package com.longbridge.sams.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private final String username_param = "susername";
	private final String password_param = "spassword";
	private final String usertype_param = "usertype";

	public CustomAuthenticationFilter() {
		super(new AntPathRequestMatcher("/login", "POST"));
	}

	

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}
		
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		String type = obtainUserType(request);

		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}
		
		if (type == null) {
			type = "";
		}

		username = username.trim();
		String usernameUserType = String.format("%s%s%s", username, String.valueOf(Character.LINE_SEPARATOR), type);

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(usernameUserType, password);

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
	}
	
	private void setDetails(HttpServletRequest request,
			UsernamePasswordAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	private String obtainPassword(HttpServletRequest request) {
		return request.getParameter(password_param);
	}

	private String obtainUsername(HttpServletRequest request) {
		return request.getParameter(username_param);
	}

	private String obtainUserType(HttpServletRequest request) {
		return request.getParameter(usertype_param);
	}

}
