package com.longbridge.sams.repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.longbridge.sams.model.User;
import com.longbridge.sams.model.UserType;

@Repository
public interface UserRepository extends CommonRepository<User,Long> {
	
	User findByLoginIdAndStatus(String name, String status);
	User findFirstByLoginIdAndType(String name, UserType type);
	User findFirstByLoginIdAndTypeAndSchoolId(String name, UserType type, Long schoolId);
	List<User> findByIdNotIn(Long[] memberArray);
}
