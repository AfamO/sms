package com.longbridge.sams.admin.service.implementation;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.admin.service.AdminRoleService;
import com.longbridge.sams.model.Role;
import com.longbridge.sams.repository.RoleRepository;
import com.longbridge.sams.repository.UserRepository;
import com.longbridge.sams.utils.Messages;

import java.util.List;
import java.util.Locale;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleRepository roleRepo;
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private Messages messageSource;

    private ModelMapper modelMapper = new ModelMapper();

    private Locale locale = LocaleContextHolder.getLocale();



	@Override
	public Role getRole(Long Id) {
		return roleRepo.findById(Id).get();
		
	}



	@Override
	public Page<Role> findRoles(String pattern, Long schoolId, Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Role> fetchAllRoles(Long schoolId) {
		return roleRepo.findBySchoolId(schoolId);
	}

	@Override
	public Page<Role> fetchRoles(Long schoolId, Pageable page) {
		return roleRepo.findBySchoolId(schoolId,page);
	}


	@Override
	public Role createRole(Long schoolId, Role role) throws ApplicationException {
		try{
			role.setSchoolId(schoolId);
			role = roleRepo.save(role);
		}catch(Exception ex){
			log.error("Error creating role {}",role , ex);
			throw new ApplicationException(ex);
		}
		return role;
	}

	@Override
	public Role modifyRole(Long schoolId,Role role) throws ApplicationException {
		try{
			//check type
			Role oldRole = roleRepo.getOne(role.getId());
			if(!oldRole.getType().equals(role.getType())) {
				//check if users are linked to the role
				if(userRepo.countByRole(oldRole) > 0)
					throw new ApplicationException(messageSource.get("role.type.error",oldRole.getType().toString(),role.getType().toString()));
			}
			role.setSchoolId(schoolId);
			role = roleRepo.save(role);
		}catch(Exception ex){
			log.error("Error updating role {}",role , ex);
			throw new ApplicationException(ex);
		}
		return role;
	}

	@Override
	public void deleteRole(Role role) throws ApplicationException {
		throw new ApplicationException("Unimplemented");
	}


}
