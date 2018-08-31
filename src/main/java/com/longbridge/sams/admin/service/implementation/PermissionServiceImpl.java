package com.longbridge.sams.admin.service.implementation;




import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import com.longbridge.sams.model.UserType;
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
		Permission permission = repo.getOne(id);
		return permission;
	}

	@Override
	public List<Permission> getAllPermissions(UserType type) {
		log.debug("get All permissions");
		return repo.findByType(type);
	}


	@Override
	public Permission modify(Permission permission) throws ApplicationException {
		try{
			return repo.save(permission);
		}catch(Exception ex){
			log.error("Error saving permission", ex);
			throw new ApplicationException(ex.getMessage());
		}
	}

	@Override
	public Permission create(Permission permission) throws ApplicationException {
		try{
			return repo.save(permission);
		}catch(Exception ex){
			log.error("Error saving permission", ex);
			throw new ApplicationException(ex.getMessage());
		}
	}

	@Override
	public void delete(Permission permission) throws ApplicationException {
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
		
		List<Long> list = role.getPermissions().stream().map(p -> p.getId()).collect(Collectors.toList());
		// not in NULL check
		if(list.isEmpty())
			list.add(new Long(-1L));
		List<Permission> optionsNotInRole = repo.findByIdNotInAndType(list,role.getType());
		return optionsNotInRole;
	}

	@Override
	public List<Permission> getAllPermissionsInRole(Role role) {
		List<Permission> optionsInRole = repo.findByRole(role);
		return optionsInRole;
	}
	
	@Override
	public Map<String, List<Permission>> groupPermission(List<Permission> permissions){
		Map<String, List<Permission>> map = permissions.stream().collect(Collectors.groupingBy(Permission::getCategory));
		return map;
	}

}
