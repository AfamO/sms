package com.longbridge.sams.admin.rest;

import com.longbridge.sams.admin.service.implementation.SettingServiceImpl;
import com.longbridge.sams.model.Setting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setting")
public class SettingController {

    @Autowired
    private SettingServiceImpl settingService;

    @PostMapping("/create")
    public String create(Setting setting){

        String result = settingService.create(setting);

        return result;
    }

    @PostMapping("/update")
    public String update(Setting setting){

        String result = settingService.update(setting);

        return  result;
    }

    @GetMapping("/get/{id}")
    public Setting get(@PathVariable Long id){

        Setting setting = settingService.get(id);

        return setting;
    }

    @PostMapping("/delete")
    public String delete(Setting setting){

        String result = settingService.delete(setting);

        return result;
    }
}
