package com.longbridge.sams.admin.service.implementation;

import com.longbridge.sams.admin.service.SchoolService;
import com.longbridge.sams.model.School;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class SchoolServiceImpl implements SchoolService {
    @Override
    public String create(School school) {
        return null;
    }

    @Override
    public String update(School school) {
        return null;
    }

    @Override
    public String changeSchoolStatus(School school, String status) {
        return null;
    }

    @Override
    public School getSchool(Long Id) {
        return null;
    }

    @Override
    public Page<School> getSchools(Pageable pageable) {
        return null;
    }

    @Override
    public List<School> getSchools(String delFlag) {
        return null;
    }
}
