package com.longbridge.sams.admin.service.implementation;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.admin.service.SchoolService;
import com.longbridge.sams.data.dto.SchoolInfo;
import com.longbridge.sams.model.Role;
import com.longbridge.sams.model.School;
import com.longbridge.sams.model.User;
import com.longbridge.sams.model.Status;
import com.longbridge.sams.model.UserType;
import com.longbridge.sams.repository.RoleRepository;
import com.longbridge.sams.repository.SchoolRepository;
import com.longbridge.sams.repository.UserRepository;
import com.longbridge.sams.utils.Messages;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolRepository repo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	public void setRepo(SchoolRepository repo) {
		this.repo = repo;
	}

	@Autowired
	Messages Messages;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public School create(School school) throws ApplicationException {
		School result = null;
		try {
			school.setStatus(Status.ENABLED);
			result = repo.save(school);
		}
		catch (Exception e) {
			log.error("error creating school {}",school, e);
			throw new ApplicationException(e);
}
		return result;
				}

@Override
@Transactional(rollbackFor=Exception.class)
public School update(School school) throws ApplicationException {
		School result = null;
		try {
			result = repo.save(school);;
		} catch (Exception e) {
			log.error("error updating school {}",school, e);
			throw new ApplicationException(e);
		}
		return result;
	}
	



	@Override
	public School changeSchoolStatus(Long id, Status status) throws ApplicationException {
		School school = null;
		try {
			school = repo.getOne(id);
			school.setStatus(status);
			school = repo.save(school);
		} catch (Exception e) {
			log.error("error updating school with id {}",id, e);
			throw new ApplicationException(e);
		}
		
		return school;
	}

	@Override
	public School getSchool(Long Id) {
		return repo.getOne(Id);
	}

	@Override
	public Page<School> getSchools(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public List<School> getSchools(String delFlag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addDefaultUser(Long schoolId, User user) throws ApplicationException {
		// TODO Auto-generated method stub
		School school = repo.getOne(schoolId);
		user.setType(UserType.STAFF);
		user.setDelFlag("N");
		user.setCreatedOn(new Date());
		//TODO: get password expiry period
		user.setExpiryDate(DateUtils.addMonths(new Date(), 3));
		user.setLoginId(school.getCode().toLowerCase());
		//TODO: set and send password
		user.setPassword(encoder.encode(school.getCode().toLowerCase()));
		user.setStatus(Status.ENABLED);
		//user.setRole(role);
		user.setSchoolId(schoolId);
		return userRepo.save(user);
	}

	@Override
	public School create(School school, boolean adduser) throws ApplicationException {
		// TODO Auto-generated method stub
		School school2 = create(school);
		if(adduser) {
			Role role = new Role();
			role.setSchoolId(school2.getId());
			role.setName("default Role");
			role.setCreatedOn(new Date());
			role = roleRepo.save(role);
			User user = new User();
			user.setRole(role);
			User defaultUser = addDefaultUser(school2.getId(), user);
		}
		return school2;
	}

	@Override
	public SchoolInfo getSchool(String code) {
		return repo.findByCode(code);
	}


}
