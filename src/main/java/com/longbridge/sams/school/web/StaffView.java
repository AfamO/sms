package com.longbridge.sams.school.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/school/staff")
public class StaffView {

	private  final Logger logger = LoggerFactory.getLogger(this.getClass());

  	@GetMapping("/{id}/edit")
    public String editStaff(@PathVariable Long id, Model model){
  		model.addAttribute("id", id);
        return "school/staff/edit";
    }
  	
  	@GetMapping("/new")
    public String newStaff(){
        return "school/staff/edit";
    }
  	
    @GetMapping
    public String liststaff(){

        return "school/staff/list";
    }

    @GetMapping("/info")
    public String schoolInfo(){
        return "/school/staff/info";
    }
}
