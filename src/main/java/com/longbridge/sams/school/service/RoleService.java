package com.longbridge.sams.school.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.model.Role;

import java.util.List;

public interface RoleService {

    public String createRole(Role role) throws  ApplicationException;

    public String modifyRole(Role role)throws  ApplicationException;


    public String deleteRole(Role role)throws  ApplicationException;

    public Page<Role> findRoles(String pattern,Pageable page);

    public Role getRole(Long Id);

    public List<Role> fetchAllRoles();

    public Page<Role> fetchRoles(Pageable page);
    
//    public Role getDefaultUserRole()throws  ApplicationException;

}
