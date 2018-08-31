package com.longbridge.sams.school.service.implementation;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.model.Status;
import com.longbridge.sams.utils.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.longbridge.sams.model.Student;
import com.longbridge.sams.repository.StudentRepo;
import com.longbridge.sams.school.service.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	com.longbridge.sams.utils.Messages Messages;
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentRepo studentRepo;
	
	@Override
	public Student createStudent(Student student) {
		Student result = null;
		try {
			result = studentRepo.save(student);
		}
		catch (Exception e) {
			log.error("error creating school {}",student, e);

		}
		return result;
	}


	@Override
	public Student deleteStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student updateStudent(Student student) {
		Student result = null;
		try {
			result = studentRepo.save(student);
		} catch (Exception e) {
			log.error("error updating student {}",student, e);
		}
		return result;
	}

	@Override
	public Student getStudent(Long id) {
		// TODO Auto-generated method stub
		return studentRepo.getOne(id);
	}

	@Override
	public Page<Student> getStudents(Pageable pageable) {
		// TODO Auto-generated method stub
		return studentRepo.findAll(pageable);
	}

}
