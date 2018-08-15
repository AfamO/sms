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
import com.longbridge.sams.model.School;
import com.longbridge.sams.utils.CustomBeanUtilsBean;
import com.longbridge.sams.utils.DataTablesUtils;


import javax.validation.Valid;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/school")
public class SchoolController {

	private static final Logger logger = LoggerFactory.getLogger(SchoolController.class);

	@Autowired
	SchoolService schoolService;

	@PostMapping("/save")
	public ResponseEntity<?>  createOrUpdateSchool(@RequestBody @Valid School school,  Errors err) {
		logger.info("schoolDTO received is {} " + school);

		School response = null;
		ResponseEntity<?> resp = null;
		try {
			if(err.hasErrors()) {
				return ResponseEntity.badRequest().body(err);
			}
			if (school.getId() != null) {
				School sch2 = schoolService.getSchool(school.getId());
				CustomBeanUtilsBean.getInstance().copyProperties(sch2, school);
				school = sch2;
				response = schoolService.update(school);
				resp = new ResponseEntity<>(response,HttpStatus.OK);
			} else {
				response = schoolService.create(school);
				resp = new ResponseEntity<>(response,HttpStatus.OK);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.debug("Error Adding {}",school,ex);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(school);
		}
		
		return resp;
	}

	@GetMapping("{id}/view")
	ResponseEntity<School> getSchool(@PathVariable Long id) {
		School school = schoolService.getSchool(id);
		return ResponseEntity.ok(school);
	}

	
	@GetMapping
	public DataTablesOutput<School> getSchools(@RequestBody DataTablesInput input) {
		Pageable pageable = DataTablesUtils.getPageable(input);

		Page<School> schools = null;
//		if (StringUtils.isNoneBlank(search)) {
//			codes = codeService.findCode(search, pageable);
//		} else {
		schools = schoolService.getSchools(pageable);
//		}
		DataTablesOutput<School> out = new DataTablesOutput<School>();
		out.setDraw(input.getDraw());
		out.setData(schools.getContent());
		out.setRecordsFiltered(schools.getTotalElements());
		out.setRecordsTotal(schools.getTotalElements());
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
