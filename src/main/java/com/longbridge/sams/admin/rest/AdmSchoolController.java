package com.longbridge.sams.admin.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import com.longbridge.sams.admin.service.SchoolService;
import com.longbridge.sams.data.dto.FieldError;
import com.longbridge.sams.data.dto.ResponseData;
import com.longbridge.sams.model.School;
import com.longbridge.sams.utils.CustomBeanUtilsBean;
import com.longbridge.sams.utils.DataTablesUtils;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/v1/school")
public class AdmSchoolController {

	private static final Logger logger = LoggerFactory.getLogger(AdmSchoolController.class);

	@Autowired
	SchoolService schoolService;

	
	
	@GetMapping
	public DataTablesOutput<School> getSchools(DataTablesInput input) {
		Pageable pageable = DataTablesUtils.getPageable(input);

		Page<School> schools = null;
		schools = schoolService.getSchools(pageable);
//		}
		DataTablesOutput<School> out = new DataTablesOutput<School>();
		out.setDraw(input.getDraw());
		out.setData(schools.getContent());
		out.setRecordsFiltered(schools.getTotalElements());
		out.setRecordsTotal(schools.getTotalElements());
		return out;
	}

}
