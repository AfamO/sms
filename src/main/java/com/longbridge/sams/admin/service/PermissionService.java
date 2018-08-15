package com.longbridge.sams.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.model.Permission;
import com.longbridge.sams.model.Role;

import java.util.List;

public interface PermissionService {

    public String create(Permission permission) throws ApplicationException;

    public String modify(Permission permission)throws ApplicationException;

    public String delete(Permission permission)throws ApplicationException;

    public Permission get(Long Id);

    public List<Permission> getAllPermissions();

    public Page<Permission> getPermissions(Pageable page);
    
    List<Permission> getAllPermissionsNotInRole(Role role);

	Page<Permission> findPermission(String pattern, Pageable page);

	List<Permission> getAllPermissionsInRole(Role role);


}
