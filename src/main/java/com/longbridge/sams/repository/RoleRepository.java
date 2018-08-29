package com.longbridge.sams.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.longbridge.sams.model.Role;

public interface RoleRepository extends CommonRepository<Role, Long> {

	Page<Role> findByNameContaining(String name ,Pageable details);

	List<Role> findByName(String string);
	
	Page<Role> findBySchoolId( Long schoolId, Pageable page);
	
	List<Role> findBySchoolId( Long schoolId);
}
