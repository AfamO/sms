package com.longbridge.sams.admin.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import com.longbridge.sams.admin.service.SchoolService;
import com.longbridge.sams.model.School;

import java.util.List;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/school")
public class SchoolController {

	private static final Logger logger = LoggerFactory.getLogger(SchoolController.class);

	@Autowired
	SchoolService schoolService;

	@PostMapping("/save")
	public String createOrUpdateSchool(@RequestBody School schoolDTO) {
		logger.info("schoolDTO received is {} " + schoolDTO);
		logger.info("schoolId is {} ", schoolDTO.getId());
		System.out.println("school id" + schoolDTO.getId());
		System.out.println("school is" + schoolDTO.toString());

		if (schoolDTO.getId() != null) {
			return schoolService.update(schoolDTO);
		} else {
			logger.debug("creating new school");
			return schoolService.create(schoolDTO);
		}
	}

//    @GetMapping("/edit/{id}")
//    public String editSchool(@PathVariable Long id) {
//        logger.debug("Opening School with id {} for update", id);
//        School school = schoolService.getSchool(id);
//        return "success";
//    }

	@GetMapping("/list")
	public List<School> getAllSchools() {
		logger.debug("school list is {} ");
		List<School> schoolList = schoolService.getSchools("N");
		logger.debug("Number of schoolDTOList is: " + schoolList.size());
		return schoolList;

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
