package com.longbridge.sams.admin.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import com.longbridge.sams.admin.service.SchoolService;
import com.longbridge.sams.model.School;
import com.longbridge.sams.utils.CustomBeanUtilsBean;
import com.longbridge.sams.utils.DataTablesUtils;


import javax.validation.Valid;

@Controller
@RequestMapping("/admin/school")
public class SchoolView {

	private static final Logger logger = LoggerFactory.getLogger(SchoolView.class);

	  	@GetMapping("/edit")
	    public String editSchool(){
	        return "admin/school/edit";
	    }
	  	
	    @GetMapping
	    public String listschool(){

	        return "admin/school/list";
	    }

	    @GetMapping("/info")
	    public String schoolInfo(){
	        return "admin/school/info";
	    }
}
