//package com.longbridge.sams.security.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.core.convert.converter.Converter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.longbridge.sams.model.User;
//
//
//@Service("extUserDetailsService")
//public class AuthenticationUserDetailsServiceImpl implements AuthenticationUserDetailsService<Authentication> {
//
//	private Converter<User, UserDetails> userUserDetailsConverter;
//	
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	@Qualifier(value = "userToUserDetails")
//	public void setUserUserDetailsConverter(Converter<User, UserDetails> userUserDetailsConverter) {
//		this.userUserDetailsConverter = userUserDetailsConverter;
//	}
//	
//	@Override
//	public UserDetails loadUserDetails(Authentication token) throws UsernameNotFoundException {
//		return userUserDetailsConverter.convert(userService.getUserForLoginByName(token.getName()));
//	}
//
//}
//
