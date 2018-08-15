package com.longbridge.sams.admin.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.model.Setting;




public interface SettingService{

    String addSetting( Setting setting) throws ApplicationException;

    Setting getSetting(Long id);

    Setting getSettingByName(String name);

    Iterable<Setting> getSettings();

    Page<Setting> getSettings(Pageable pageDetails);
    
    Page<Setting> getSettings(String pattern, Pageable pageDetails);
    
    String updateSetting(Setting setting) throws ApplicationException;

    String deleteSetting(Long id) throws ApplicationException;

}
