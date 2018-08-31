package com.longbridge.sams.admin.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/setting")
public class SettingView {

    private static final Logger logger = LoggerFactory.getLogger(SettingView.class);

    @GetMapping("/create")
    public String create(){ return "admin/settings/create";}

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id){ return "admin/settings/edit"; }

    @GetMapping("/view/{id}")
    public String view(@PathVariable Long id){ return "admin/settings/view"; }

    @GetMapping("/list")
    public  String list(){ return  "admin/settings/list"; }
}
