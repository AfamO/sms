package com.longbridge.sams.admin.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.longbridge.sams.admin.service.AdminRoleService;
import com.longbridge.sams.admin.service.SchoolService;
import com.longbridge.sams.model.Role;
import com.longbridge.sams.utils.DataTablesUtils;

@RestController
@RequestMapping("/admin/v1/school/")
public class AdmRoleController {


	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AdminRoleService  roleservice;
	@Autowired
	SchoolService schoolService;

	
	


	@GetMapping("{schoolId}/roles")
	public DataTablesOutput<Role> getUser(DataTablesInput input,@PathVariable Long schoolId, Model model) {
		Pageable pageable = DataTablesUtils.getPageable(input);

		Page<Role> roles = null;
		roles = roleservice.fetchRoles(schoolId,pageable);
//		}
		DataTablesOutput<Role> out = new DataTablesOutput<Role>();
		out.setDraw(input.getDraw());
		out.setData(roles.getContent());
		out.setRecordsFiltered(roles.getTotalElements());
		out.setRecordsTotal(roles.getTotalElements());
		return out;
	}

}
