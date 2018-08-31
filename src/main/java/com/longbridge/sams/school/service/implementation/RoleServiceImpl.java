package com.longbridge.sams.school.service.implementation;


import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.model.Role;
import com.longbridge.sams.repository.RoleRepository;
import com.longbridge.sams.school.service.RoleService;
import com.longbridge.sams.utils.Messages;

import java.util.List;
import java.util.Locale;

@Service
public class RoleServiceImpl implements RoleService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private Messages messageSource;

    private ModelMapper modelMapper = new ModelMapper();

    private Locale locale = LocaleContextHolder.getLocale();

	public String createRole(Role role) throws ApplicationException{
		String result = "" ;
		try{
			roleRepo.save(role);
			result= messageSource.get("role.update.success");
		}catch(Exception ex){
			result= messageSource.get("role.update.error");
			log.error(result, ex);
			throw new ApplicationException(result);
		}
		return result;
	}

	@Override
	public String modifyRole(Role role) throws ApplicationException{
		String result = "" ;
		try{
			roleRepo.save(role);
			result= messageSource.get("role.update.success");
		}catch(Exception ex){
			result= messageSource.get("role.update.error");
			log.error(result, ex);
			throw new ApplicationException(result);
		}
		return result;
	}

	@Override
	public String deleteRole(Role role) throws ApplicationException{
		throw new ApplicationException("Unimplemented");
	}

	@Override
	public Role getRole(Long Id) {
		return roleRepo.findById(Id).get();
		
	}

	@Override
	public List<Role> fetchAllRoles() {
		return roleRepo.findAll();
	}

	@Override
	public Page<Role> fetchRoles(Pageable page) {
		return roleRepo.findAll(page);
	}

	@Override
	public Page<Role> findRoles(String pattern, Pageable page) {
		return roleRepo.findByNameContaining(pattern, page);
	}



}
