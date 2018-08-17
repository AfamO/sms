package com.longbridge.sams.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/code")
public class CodeView {

	@GetMapping("/{id}/edit")
    public String editCode(@PathVariable("id")String id, Model model){
		model.addAttribute("id",id);
        return "admin/code/edit";
    }
  	
    @GetMapping("/type")
    public String listTypes(){

        return "admin/code/list-type";
    }
    
    @GetMapping("/type/{codetype}")
    public String listcode(@PathVariable("codetype") String codeType, Model model){
    	model.addAttribute("codetype",codeType);
        return "admin/code/list-type-code";
    }

    @GetMapping("/info")
    public String codeInfo(){
        return "code/info";
    }
}
