package com.longbridge.sams.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/permission")
public class PermissionView {

    @GetMapping("/edit")
    String createPermission() {
        return "admin/permission/edit";
    }


    @GetMapping("/list")
    String viewPermission() {
        return "admin/permission/list";
    }

}
