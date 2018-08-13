package com.longbridge.sams.admin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class MainController {
	
	@GetMapping("index")
	String getIndex() {
		return "admin/index";
	}
	
	
	@GetMapping
	String getDashboard() {
		return "admin/index";
	}

}
