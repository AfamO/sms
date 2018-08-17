package com.longbridge.sams.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/codes")
public class CodeView {

	@GetMapping("/edit")
    public String editCode(){
        return "admin/code/edit";
    }
  	
    @GetMapping("/list")
    public String listcode(){

        return "admin/code/list-type-code";
    }

    @GetMapping("/add")
    public String addcode(){ return "admin/code/add"; }

    @GetMapping("/info")
    public String codeInfo(){
        return "admin/code/info";
    }
}
