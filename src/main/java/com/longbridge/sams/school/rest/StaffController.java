package com.longbridge.sams.school.rest;

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

import com.longbridge.sams.data.dto.FieldError;
import com.longbridge.sams.data.dto.ResponseData;
import com.longbridge.sams.model.Staff;
import com.longbridge.sams.school.service.StaffService;
import com.longbridge.sams.utils.CustomBeanUtilsBean;
import com.longbridge.sams.utils.DataTablesUtils;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/school/v1/staff")
public class StaffController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StaffService staffService;


	@GetMapping
	public DataTablesOutput<Staff> getStaff(DataTablesInput input) {
		Pageable pageable = DataTablesUtils.getPageable(input);

		Page<Staff> staffers = null;

		staffers = staffService.getStaff(pageable);

		DataTablesOutput<Staff> out = new DataTablesOutput<Staff>();
		out.setDraw(input.getDraw());
		out.setData(staffers.getContent());
		out.setRecordsFiltered(staffers.getTotalElements());
		out.setRecordsTotal(staffers.getTotalElements());
		return out;
	}



	@RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
	public ResponseEntity handle() {
		return new ResponseEntity(HttpStatus.OK);

	}

	@ExceptionHandler(Exception.class)
	public void handleException(Exception ex, WebRequest webRequest) {
		logger.error("an error occur here {} ", ex);
		ex.printStackTrace();
	}

}
