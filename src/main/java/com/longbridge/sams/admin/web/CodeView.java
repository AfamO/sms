package com.longbridge.sams.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/codes")
public class CodeView {

	@GetMapping("/edit")
    public String editCode(){
        return "code/edit";
    }
  	
    @GetMapping("/list")
    public String listcode(){

        return "code/list";
    }

    @GetMapping("/info")
    public String codeInfo(){
        return "code/info";
    }
}
