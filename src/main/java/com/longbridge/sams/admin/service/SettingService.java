package com.longbridge.sams.admin.service;

import com.longbridge.sams.model.Setting;

public interface SettingService {

    String create(Setting setting);

    String update(Setting setting);

    Setting get(Long id);

    String delete(Setting setting);

}
