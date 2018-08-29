package com.longbridge.sams.admin.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.model.Setting;




public interface AdmSettingService{

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    String addSetting( Setting setting) throws ApplicationException;

    Setting getSetting(Long id);

    Setting getSettingByName(String name);

    Iterable<Setting> getSettings();

    Page<Setting> getSettings(Pageable pageDetails);
    
    Page<Setting> getSettings(String pattern, Pageable pageDetails);
    
    String updateSetting(Setting setting) throws ApplicationException;

    String deleteSetting(Long id) throws ApplicationException;

}
