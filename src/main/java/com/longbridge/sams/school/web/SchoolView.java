package com.longbridge.sams.school.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/school")
public class SchoolView {
	
	@GetMapping("index")
	String getIndex() {
		return "school/index";
	}
	
	
	@GetMapping
	String getDashboard() {
		return "school/index";
	}

}
