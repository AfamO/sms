package com.longbridge.sams.admin.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.model.Status;
import com.longbridge.sams.model.User;
import com.longbridge.sams.model.UserType;



public interface UserService {
	User getUser(long id);
	
	Page<User> getUsers(Long schoolId, Pageable page);
	Page<User> getUsers(Long schoolId, UserType type,Pageable page);
	User updateUser(User user ) throws ApplicationException;
	User createUser(Long schoolId,User user ) throws ApplicationException;
	User changeStatusUser(User user, Status status ) throws ApplicationException;
	boolean resetPassword(User user) throws ApplicationException;
	
}
