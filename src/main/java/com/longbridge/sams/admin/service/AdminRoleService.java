package com.longbridge.sams.admin.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.model.Role;

import java.util.List;

public interface AdminRoleService {

    public Role createRole(Long schoolId,Role role) throws  ApplicationException;

    public Role modifyRole(Long schoolId,Role role)throws  ApplicationException;


    public void deleteRole(Role role)throws  ApplicationException;

    public Page<Role> findRoles(String pattern,Long schoolId,Pageable page);

    public Role getRole(Long Id);

    public List<Role> fetchAllRoles(Long schoolId);

    public Page<Role> fetchRoles(Long schoolId,Pageable page);
    
//    public Role getDefaultUserRole(Long schoolId)throws  ApplicationException;

}
