package com.longbridge.sams.admin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.longbridge.sams.model.School;


public interface SchoolService {

	School create(School school);

	School update(School school);

	School changeSchoolStatus(Long Id, String status);

    School getSchool(Long Id);

    Page<School> getSchools(Pageable pageable);

    List<School> getSchools(String delFlag);
}
