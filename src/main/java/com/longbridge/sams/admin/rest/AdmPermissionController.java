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
import com.longbridge.sams.utils.CustomBeanUtilsBean;
import com.longbridge.sams.utils.DataTablesUtils;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin/v1/permission")
public class 	AdmPermissionController {

	private static final Logger logger = LoggerFactory.getLogger(AdmPermissionController.class);

	@Autowired
	PermissionService permissionService;

	@PostMapping(consumes="application/json")
	public ResponseEntity<?>  createOrUpdatePermission(@RequestBody @Valid Permission permission,  Errors errors) {
		logger.info("permissionDTO received is {} " + permission);

		Permission response = null;
		ResponseEntity<?> resp = null;
		ResponseData dt = new ResponseData();
		try {
			if(errors.hasErrors()) {
				List<FieldError> err_summary = errors.getFieldErrors().stream().map(f -> new FieldError(f.getField(),f.getDefaultMessage())).collect(Collectors.toList());
				dt.setError(err_summary);
				return ResponseEntity.badRequest().body(dt);
			}
			if (permission.getId() != null) {
				Permission sch2 = permissionService.get(permission.getId());
				CustomBeanUtilsBean.getInstance().copyProperties(sch2, permission);
				permission = sch2;
				response = permissionService.modify(permission);
				resp = new ResponseEntity<>(new ResponseData (response),HttpStatus.OK);
			} else {
				response = permissionService.create(permission);
				resp = new ResponseEntity<>( new ResponseData (response),HttpStatus.OK);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.debug("Error Adding {}",permission,ex);
			ResponseData responseData = new ResponseData ();
			responseData.setError(ex.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
		}
		
		return resp;
	}

	@GetMapping("{id}/view")
	ResponseEntity<?> getPermission(@PathVariable Long id) {
		Permission permission = permissionService.get(id);
		return ResponseEntity.ok(new ResponseData(permission));
	}

	
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
