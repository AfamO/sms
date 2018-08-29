package com.longbridge.sams.repository;

import org.springframework.stereotype.Repository;

import com.longbridge.sams.data.dto.SchoolInfo;
import com.longbridge.sams.model.School;



@Repository
public interface SchoolRepository extends CommonRepository<School, Long> {

	SchoolInfo findByCode(String code);
}
