package com.longbridge.sams.config;


import java.util.Date;
import java.util.Set;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.model.Permission;
import com.longbridge.sams.model.Role;
import com.longbridge.sams.model.User;
import com.longbridge.sams.model.UserType;
import com.longbridge.sams.repository.PermissionRepository;
import com.longbridge.sams.repository.RoleRepository;
import com.longbridge.sams.repository.UserRepository;


@Component
public class AppInit implements InitializingBean {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	 

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private ResourceLoader resourceLoader;
	
	@Autowired
	private PasswordEncoder  encoder;

	@Autowired
	@Lazy
	private PermissionRepository permissionRepo;
	@Autowired
	@Lazy
	private RoleRepository rolerepository;

	private Role defaultRole;

	@Transactional
	@Override
	public void afterPropertiesSet() throws Exception {

		boolean createAdm = userRepo.count() <= 0;
		if (!createAdm)
			return;

		if (createAdm)
			createDefaultAdmin(createDefaultRole(null));

	}


	private Role createDefaultRole(Set<Permission> permissions) {
		Role role = new Role();
		role.setDelFlag("N");
		role.setName("Default Admin Profile");
		role.setEmail("test@yahoo.com");
		role.setPermissions(permissions);
		logger.info("Creating default Profile ....");
		defaultRole = rolerepository.save(role);
		return defaultRole;
	}


	private void createDefaultAdmin(Role role) throws ApplicationException {

		User user = new User();
		user.setDelFlag("N");
		// user.set
		user.setLoginId("su");
		user.setType(UserType.ADMIN);
		user.setStatus("E");
		Date d = DateUtils.addDays(new Date(), 30);
		user.setRole(role);
		user.setExpiryDate(d);
		user.setPasswordExpiryDate(d);

		try {
			user.setPassword(encoder.encode("su"));
			userRepo.save(user);
		} catch (Exception e) {
			logger.error("Can't create user", e);
			e.printStackTrace();
		}
	}

}
