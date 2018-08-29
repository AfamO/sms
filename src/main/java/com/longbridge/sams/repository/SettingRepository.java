package com.longbridge.sams.repository;



import com.longbridge.sams.model.Setting;

public interface SettingRepository extends CommonRepository<Setting, Long> {

	Setting findFirstByName(String name);
	
}
