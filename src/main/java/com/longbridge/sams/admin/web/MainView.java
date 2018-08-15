package com.longbridge.sams.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class MainView {
	
	@GetMapping("index")
	String getIndex() {
		return "admin/index";
	}
	
	
	@GetMapping
	String getDashboard() {
		return "admin/index";
	}


	@GetMapping("/createschool")
	String createSchool(){ return  "admin/school/create"; }

	@GetMapping("/school/edit/{id}")
	String editSchool(){ return  "admin/school/edit"; }

	@GetMapping("/school/list")
	String listSchool(){ return  "admin/school/list"; }

	@GetMapping("/setting")
	String setting(){ return  "admin/settings/create"; }

	@GetMapping("/setting/edit/{id}")
	String editSetting(){ return  "admin/settings/edit"; }

}
