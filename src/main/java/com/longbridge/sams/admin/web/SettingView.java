package com.longbridge.sams.admin.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/setting")
public class SettingView {

    private static final Logger logger = LoggerFactory.getLogger(SettingView.class);

    @GetMapping("/create")
    public String create(){ return "admin/settings/create";}

    @GetMapping("/edit")
    public String edit(){ return "admin/settings/edit"; }

    @GetMapping("/view")
    public String view(){ return "admin/settings/view"; }
}
