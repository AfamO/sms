package com.longbridge.sams.school.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.longbridge.sams.model.Student;
import org.springframework.stereotype.Service;

public interface StudentService {
	
    Student createStudent(Student student) ;

    Student deleteStudent(Student student);

    Student updateStudent(Student student) ;

    Student getStudent(Long id);

    Page<Student> getStudents(Pageable pageable);

}
