package com.longbridge.sams.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.longbridge.sams.model.Permission;
import com.longbridge.sams.model.Role;


public interface PermissionRepository extends CommonRepository<Permission, Long> {
	List<Permission> findByIdNotIn(Long[] permissions);
	
//	List<Permission> findByRole(@Param("role") Role role);
//	
	
}
