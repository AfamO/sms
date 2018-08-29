package com.longbridge.sams.admin.service.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.admin.service.UserService;
import com.longbridge.sams.model.Code;
import com.longbridge.sams.model.School;
import com.longbridge.sams.model.Status;
import com.longbridge.sams.model.User;
import com.longbridge.sams.model.UserType;
import com.longbridge.sams.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository repo;
	
	@Override
	public User getUser(long id) {
		return repo.getOne(id);
	}

	@Override
	public Page<User> getUsers(Long schoolId,  Pageable page) {
		return repo.findBySchoolId(schoolId,page);
	}

	@Override
	public Page<User> getUsers(Long schoolId, UserType type, Pageable page) {
		return repo.findBySchoolIdAndType(schoolId,type,page);
	}

	@Override
	public User updateUser(User user) throws ApplicationException {
		User result = null;
		try {
			result = repo.save(user);
		}
		catch (Exception e) {
			log.error("Error updating user {}",user, e);
			throw new ApplicationException(e);
		}
		return result;
	}

	@Override
	public User createUser(Long schoolId, User user) throws ApplicationException {
		User result = null;
		try {
			user.setSchoolId(schoolId);
			result = repo.save(user);
		}
		catch (Exception e) {
			log.error("Error creating user {}",user, e);
			throw new ApplicationException(e);
		}
		return result;
	}

	@Override
	public User changeStatusUser(User user, Status status) throws ApplicationException {
		User result = repo.getOne(user.getId());
		try {
			result.setStatus(status);
			result = repo.save(result);
		}
		catch (Exception e) {
			log.error("Error setting user  {} status {}",user,status, e);
			throw new ApplicationException(e);
		}
		return result;
	}

	@Override
	public boolean resetPassword(User user) throws ApplicationException {
		// TODO Auto-generated method stub
		return false;
	}

}
