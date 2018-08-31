package com.longbridge.sams.admin.service.implementation;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.admin.service.AdmSettingService;
import com.longbridge.sams.model.Setting;
import com.longbridge.sams.repository.SettingRepository;
import com.longbridge.sams.utils.Messages;


@Service
public class SettingServiceImpl implements  AdmSettingService {

	@Autowired
	private SettingRepository repo ;
	@Autowired
	Messages messageSource;
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Override  
	public String addSetting(Setting setting) throws ApplicationException {
		String result = "" ;
		try{
			repo.save(setting);
			result= messageSource.get("setting.add.success");
		}catch(Exception ex){
			result= messageSource.get("setting.add.error");
			log.error(result, ex);
			throw new ApplicationException(result);
		}
		return result;
	
	}

	@Override
	public Setting getSetting(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public Setting getSettingByName(String name) {
		return repo.findFirstByName(name);
	}

	@Override
	public Iterable<Setting> getSettings() {
		return repo.findAll();
	}

	@Override
	public Page<Setting> getSettings(Pageable pageDetails) {
		return repo.findAll(pageDetails);
	}

	@Override 
	public String updateSetting(Setting setting) throws ApplicationException {
		String result = "" ;
		try{
			repo.save(setting);
			result= messageSource.get("setting.update.success");
		}catch(Exception ex){
			result= messageSource.get("setting.update.error");
			log.error(result, ex);
			throw new ApplicationException(result);
		}
		return result;
	}

	@Override
	public String deleteSetting(Long id) throws ApplicationException {
		throw new ApplicationException("Unimplemted");
	}

	@Override
	public Page<Setting> getSettings(String pattern, Pageable pageDetails) {
		return repo.findUsingPattern(pattern, pageDetails);
	}

}
