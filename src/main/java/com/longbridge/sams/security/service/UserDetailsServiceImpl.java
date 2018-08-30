package com.longbridge.sams.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.SchoolContext;
import com.longbridge.sams.model.User;
import com.longbridge.sams.model.UserType;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService, ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private LoginUserService userService;
	@Autowired
	private Converter<User, UserDetails> userUserDetailsConverter;

	@Autowired
	@Qualifier(value = "userToUserDetails")
	public void setUserUserDetailsConverter(Converter<User, UserDetails> userUserDetailsConverter) {
		this.userUserDetailsConverter = userUserDetailsConverter;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userUserDetailsConverter.convert(userService.getUserForLoginByName(username));
	}

	@Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
		UserDetailsImpl ud = (UserDetailsImpl) event.getAuthentication().getPrincipal();
		String userName = ud.getUsername();
//		boolean pwdEpired = user.getPasswordExpiryDate().before(new Date());

		userService.log(userName, ud.getType());

	}

}
