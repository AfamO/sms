package com.longbridge.sams.school.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.longbridge.sams.model.Student;
import com.longbridge.sams.repository.StudentRepo;
import com.longbridge.sams.school.service.StudentService;

public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentRepo;
	
	@Override
	public String createStudent(Student student) {
		// TODO Auto-generated method stub
		studentRepo.save(student);
		return "";
	}

	@Override
	public String deleteStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateStudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudent(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Student> getStudents(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
