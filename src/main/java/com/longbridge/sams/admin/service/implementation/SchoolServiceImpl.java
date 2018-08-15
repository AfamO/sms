package com.longbridge.sams.admin.service.implementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.admin.service.SchoolService;
import com.longbridge.sams.model.School;
import com.longbridge.sams.repository.SchoolRepository;
import com.longbridge.sams.utils.Messages;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolRepository repo;
	public void setRepo(SchoolRepository repo) {
		this.repo = repo;
	}

	@Autowired
	Messages Messages;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public School create(School school) {
		School result = null;
		try {
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
	public School update(School school) {
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
	public School changeSchoolStatus(Long id, String status) {
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

}
