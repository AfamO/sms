package com.longbridge.sams.school.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.longbridge.sams.model.Student;
import org.springframework.stereotype.Service;

public interface StudentService {
	
    String createStudent(Student student) ;

    String deleteStudent(Student student);

    String updateStudent(Student student) ;

    Student getStudent(Long id);

    Page<Student> getStudents(Pageable pageable);

}
