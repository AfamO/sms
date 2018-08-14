package com.longbridge.sams.admin.service.implementation;

import com.longbridge.sams.admin.service.SettingService;
import com.longbridge.sams.model.Setting;
import com.longbridge.sams.repository.SettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettingServiceImpl implements SettingService {

    @Autowired
    private SettingRepository settingRepo;


    @Override
    public String create(Setting setting) {

        String result;
        try{
            settingRepo.save(setting);

            result = "created";

        }catch (Exception ex){

            result = "failed";
        }
        return result;
    }

    @Override
    public String update(Setting setting) {

        String result;
        try{

            settingRepo.save(setting);
            result = "settings updated";
        }catch(Exception ex){

            result = "update failed0";
        }
        return result;
    }

    @Override
    public Setting get(Long id) {

        Setting result=null;

        Optional<Setting> tempSetting = settingRepo.findById(id);

        if(tempSetting.isPresent()){
                Setting setting = tempSetting.get();
                result  = setting;
        }

        return result;
    }

    @Override
    public String delete(Setting setting) {

        String result;
        try{

            setting.setDelFlag("Y");
            settingRepo.save(setting);

            result = "deleted";
        }catch(Exception ex){

            result = "not deleted";
        }
        return result;
    }
}
