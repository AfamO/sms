package com.longbridge.sams.school.rest;

import com.longbridge.sams.model.Student;
import com.longbridge.sams.school.service.StudentService;
import com.longbridge.sams.utils.DataTablesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/school/v1/student")
public class StudentRestController {

    private static final Logger logger = LoggerFactory.getLogger(StudentRestController.class);

    @Autowired
    StudentService studentService;


    @GetMapping
    public DataTablesOutput<Student> getStudents(DataTablesInput input) {
        Pageable pageable = DataTablesUtils.getPageable(input);

        Page<Student> students = null;
        students = studentService.getStudents(pageable);
        DataTablesOutput<Student> out = new DataTablesOutput<Student>();
        out.setDraw(input.getDraw());
        out.setData(students.getContent());
        out.setRecordsFiltered(students.getTotalElements());
        out.setRecordsTotal(students.getTotalElements());
        return out;
    }

}
