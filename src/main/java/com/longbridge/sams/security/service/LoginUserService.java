package com.longbridge.sams.security.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longbridge.sams.model.User;
import com.longbridge.sams.model.UserType;
import com.longbridge.sams.repository.UserRepository;

@Service
@Transactional
public class LoginUserService {

	@Autowired
	UserRepository userrepo;

	@Transactional
	public User getUserForLoginByName(String userName) {
		
		String[] usernameAndType = StringUtils.split(userName, ":");
		if(usernameAndType.length <2)
			return null;
		String type = usernameAndType[1];
		String name = usernameAndType[0];
		if (StringUtils.isEmpty(type)) {
			return null;
		}

		User user = null;

		switch (type) {
		case "admin":
			user = userrepo.findFirstByLoginIdAndType(name, UserType.ADMIN);
			break;
		case "school":
			if(usernameAndType.length >= 2) {
				String sid = usernameAndType[2];
				Long schoolid = NumberUtils.createLong(sid);
				user = userrepo.findFirstByLoginIdAndTypeAndSchoolId(name, UserType.STAFF,schoolid);
			}
			break;
		case "parent":
			user = userrepo.findFirstByLoginIdAndType(name, UserType.PARENT);
			break;
		case "student":
			user = userrepo.findFirstByLoginIdAndType(name, UserType.STUDENT);
			break;
		default:
			user = null;
		}
		return user;
	}

	public void log(String username, UserType type) {
		User user = userrepo.findFirstByLoginIdAndType(username, type);
		user.setLastLoginDate(new Date());
		userrepo.save(user);
	}


}
