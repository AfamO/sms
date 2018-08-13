package com.longbridge.sams.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.longbridge.sams.model.User;
import com.longbridge.sams.security.service.UserDetailsImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Component
public class UserToUserDetails implements Converter<User, UserDetails> {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public UserDetails convert(User user) {
		UserDetailsImpl userDetails = new UserDetailsImpl();

		if (user != null) {
			userDetails.setUsername(user.getLoginId());
			userDetails.setPassword(user.getPassword());
			boolean enabled = user.getStatus().equals("E");
			boolean locked = user.getStatus().equals("L") ? true : false;
			userDetails.setEnabled(enabled);
			userDetails.setNonLocked(!locked);

			boolean expired = user.getExpiryDate().before(new Date());
			userDetails.setNotExpired(!expired);

//            boolean pwdexpired = user.getPasswordExpiryDate().before(new Date());
			boolean pwdexpired = false; // handle after login
			userDetails.setCredentialsNotExpired(!pwdexpired);

			userDetails.setRole(user.getRole().getName());
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
			user.getRole().getPermissions().forEach(permission -> {
				authorities.add(new SimpleGrantedAuthority(permission.getName()));
			});
			String primaryRole = "";

			switch (user.getType()) {
			case ADMIN:
				primaryRole = "ROLE_ADMIN";
				break;
			case PARENT:
				primaryRole = "ROLE_PARENT";
				break;
			case STAFF:
				primaryRole = "ROLE_STAFF";
				break;
			case STUDENT:
				primaryRole = "ROLE_STUDENT";
				break;
			default:
				primaryRole = "ROLE_NONE";
			}
			authorities.add(new SimpleGrantedAuthority(primaryRole));
			userDetails.setAuthorities(authorities);
			userDetails.setType(user.getType());
		}

		return userDetails;
	}
}
