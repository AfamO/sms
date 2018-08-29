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

	@PostMapping(consumes="application/json")
	public ResponseEntity<?>  createOrUpdateStaff(@RequestBody @Valid Staff staff,  Errors errors) {
		logger.info("staff received is {} " + staff);

		Staff response = null;
		ResponseEntity<?> resp = null;
		ResponseData dt = new ResponseData();
		try {
			if(errors.hasErrors()) {
				List<FieldError> err_summary = errors.getFieldErrors().stream().map(f -> new FieldError(f.getField(),f.getDefaultMessage())).collect(Collectors.toList());
				dt.setError(err_summary);
				return ResponseEntity.badRequest().body(dt);
			}
			if (staff.getId() != null) {
				Staff staff2 = staffService.getStaff(staff.getId());
				CustomBeanUtilsBean.getInstance().copyProperties(staff2, staff);
				staff = staff2;
				response = staffService.updateStaff(staff);
				resp = new ResponseEntity<>(new ResponseData (response),HttpStatus.OK);
			} else {
				response = staffService.createStaff(staff);
				resp = new ResponseEntity<>( new ResponseData (response),HttpStatus.OK);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.debug("Error Adding {}",staff,ex);
			ResponseData responseData = new ResponseData ();
			responseData.setError(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
		}
		
		return resp;
	}

	@GetMapping("{id}/view")
	ResponseEntity<?> getStaff(@PathVariable Long id) {
		Staff staff = staffService.getStaff(id);
		return ResponseEntity.ok(new ResponseData(staff));
	}

	
	@GetMapping
	public DataTablesOutput<Staff> getStaff(DataTablesInput input) {
		Pageable pageable = DataTablesUtils.getPageable(input);

		Page<Staff> staffers = null;
//		if (StringUtils.isNoneBlank(search)) {
//			codes = codeService.findCode(search, pageable);
//		} else {
		staffers = staffService.getStaff(pageable);
//		}
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
