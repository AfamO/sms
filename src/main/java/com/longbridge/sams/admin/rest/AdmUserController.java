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
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import com.longbridge.sams.admin.service.SchoolService;
import com.longbridge.sams.admin.service.UserService;
import com.longbridge.sams.data.dto.FieldError;
import com.longbridge.sams.data.dto.ResponseData;
import com.longbridge.sams.model.School;
import com.longbridge.sams.model.User;
import com.longbridge.sams.repository.UserRepository;
import com.longbridge.sams.utils.CustomBeanUtilsBean;
import com.longbridge.sams.utils.DataTablesUtils;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/v1/school/")
public class AdmUserController {


	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserService  userservice;
	@Autowired
	SchoolService schoolService;

	
	


	@GetMapping("{schoolId}/users")
	public DataTablesOutput<User> getUser(DataTablesInput input,@PathVariable Long schoolId, Model model) {
		Pageable pageable = DataTablesUtils.getPageable(input);

		Page<User> users = null;
//		if (StringUtils.isNoneBlank(search)) {
//			codes = codeService.findCode(search, pageable);
//		} else {
		users = userservice.getUsers(schoolId,pageable);
//		}
		DataTablesOutput<User> out = new DataTablesOutput<User>();
		out.setDraw(input.getDraw());
		out.setData(users.getContent());
		out.setRecordsFiltered(users.getTotalElements());
		out.setRecordsTotal(users.getTotalElements());
		return out;
	}

}
