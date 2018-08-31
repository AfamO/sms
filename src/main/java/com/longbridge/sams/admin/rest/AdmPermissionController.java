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

import com.longbridge.sams.admin.service.PermissionService;
import com.longbridge.sams.data.dto.FieldError;
import com.longbridge.sams.data.dto.ResponseData;
import com.longbridge.sams.model.Permission;
import com.longbridge.sams.model.UserType;
import com.longbridge.sams.utils.CustomBeanUtilsBean;
import com.longbridge.sams.utils.DataTablesUtils;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/admin/v1/permission")
public class 	AdmPermissionController {

	private static final Logger logger = LoggerFactory.getLogger(AdmPermissionController.class);

	@Autowired
	private PermissionService permissionService;

	@GetMapping
	public DataTablesOutput<Permission> getPermissions(DataTablesInput input) {
		Pageable pageable = DataTablesUtils.getPageable(input);

		Page<Permission> permissions = null;
//		if (StringUtils.isNoneBlank(search)) {
//			codes = codeService.findCode(search, pageable);
//		} else {
		permissions = permissionService.getPermissions(pageable);
//		}
		DataTablesOutput<Permission> out = new DataTablesOutput<Permission>();
		out.setDraw(input.getDraw());
		out.setData(permissions.getContent());
		out.setRecordsFiltered(permissions.getTotalElements());
		out.setRecordsTotal(permissions.getTotalElements());
		return out;
	}

	@GetMapping("/type/{usertype}")
	List<Permission> getPermissions(@PathVariable("usertype") String usertype){
		UserType type = UserType.valueOf(usertype);
		return permissionService.getAllPermissions(type);
	}

	
}
