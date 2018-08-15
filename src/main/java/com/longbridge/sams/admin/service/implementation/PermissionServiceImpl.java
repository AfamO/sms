package com.longbridge.sams.admin.service.implementation;




import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.admin.service.PermissionService;
import com.longbridge.sams.model.Permission;
import com.longbridge.sams.model.Role;
import com.longbridge.sams.repository.PermissionRepository;
import com.longbridge.sams.utils.Messages;


@Service
//@Transactional
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionRepository repo;
	@Autowired
	Messages messageSource;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public Permission get(Long id) {
		Permission permission = repo.findById(id).get();
		return permission;
	}

	@Override
	public List<Permission> getAllPermissions() {
		log.debug("get All permissions");
		return repo.findAll();
	}


	@Override
	public String modify(Permission permission) throws ApplicationException {
		String result = "" ;
		try{
			repo.save(permission);
			result= messageSource.get("permission.update.success");
		}catch(Exception ex){
			result= messageSource.get("permission.update.error");
			log.error(result, ex);
			throw new ApplicationException(result);
		}
		return result;
	}

	@Override
	public String create(Permission permission) throws ApplicationException {
		String result = "" ;
		try{
			repo.save(permission);
			result= messageSource.get("permission.create.success");
		}catch(Exception ex){
			result= messageSource.get("permission.create.error");
			log.error(result, ex);
			throw new ApplicationException(result);
		}
		return result;
	}

	@Override
	public String delete(Permission permission) throws ApplicationException {
		throw new ApplicationException("Unimplimented");
		//return null;
	}


	@Override
	public Page<Permission> getPermissions(Pageable page) {
		return repo.findAll(page);
	}

	

	@Override
	public Page<Permission> findPermission(String pattern, Pageable page) {
		
//		ExampleMatcher matcher = ExampleMatcher.matchingAll().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING).withIgnoreCase().withIgnoreNullValues();
//		return repo.findAll(Example.of(c,matcher),page);
		//return repo.findPermissions(pattern, page);
		return repo.findUsingPattern(pattern, page);
	}

	@Override
	public List<Permission> getAllPermissionsNotInRole(Role role) {
		Long[] permissionArray = new Long[role.getPermissions().size()];
		int idx = 0;
		for (Permission perm : role.getPermissions()) {
			permissionArray[idx] = perm.getId();
			idx++;
		}
		// not in NULL 1check
		if (permissionArray.length == 0)
			permissionArray = new Long[] { -1L };
		List<Permission> optionsNotInRole = repo.findByIdNotIn(permissionArray);
		return optionsNotInRole;
	}

	@Override
	public List<Permission> getAllPermissionsInRole(Role role) {
		List<Permission> optionsInRole = repo.findByRole(role);
		return optionsInRole;
	}

}
