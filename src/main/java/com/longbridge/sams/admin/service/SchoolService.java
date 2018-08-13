package com.longbridge.sams.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.longbridge.sams.model.School;


public interface SchoolService {

	String create(School school);

    String update(School school);

    String changeSchoolStatus(School school, String status);

    School getSchool(Long Id);

    Page<School> getSchools(Pageable pageable);

    List<School> getSchools(String delFlag);
}
