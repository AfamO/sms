package com.longbridge.sams.admin.rest;

import com.longbridge.sams.admin.service.implementation.SettingServiceImpl;
import com.longbridge.sams.model.Setting;
import com.longbridge.sams.utils.DataTablesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/setting")
public class SettingController {

    private static final Logger logger = LoggerFactory.getLogger(SettingController.class);

    @Autowired
    private SettingServiceImpl settingService;

    @PostMapping("/create")
    public String create(@RequestBody Setting setting){

        String result = settingService.addSetting(setting);

        return result;
    }

    @PostMapping("/update")
    public String update(@RequestBody Setting setting){

        String result = settingService.updateSetting(setting);

        return  result;
    }

    @GetMapping("/get/{id}")
    public Setting get(@PathVariable Long id){

        Setting setting = settingService.getSetting(id);

        return setting;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id){

        String result = settingService.deleteSetting(id);

        return result;
    }

    @GetMapping("/list")
    public DataTablesOutput<Setting> getSettings( DataTablesInput input) {
        Pageable pageable = DataTablesUtils.getPageable(input);

        Page<Setting> settings = null;
        settings = settingService.getSettings(pageable);

        DataTablesOutput<Setting> out = new DataTablesOutput<Setting>();
        out.setDraw(input.getDraw());
        out.setData(settings.getContent());
        out.setRecordsFiltered(settings.getTotalElements());
        out.setRecordsTotal(settings.getTotalElements());
        return out;
    }

    @GetMapping("/all")
    public  Iterable<Setting> getSettings(){

        Iterable<Setting> settings = settingService.getSettings();

        return settings;
    }

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);

    }

    @ExceptionHandler(Exception.class)
    public void handleException(Exception ex, WebRequest webRequest) {
        logger.error("an error occur here {} ", ex);
        ex.printStackTrace();
    }
}
