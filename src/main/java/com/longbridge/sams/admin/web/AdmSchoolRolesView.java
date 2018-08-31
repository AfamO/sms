package com.longbridge.sams.admin.web;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.longbridge.sams.ApplicationException;
import com.longbridge.sams.admin.service.AdminRoleService;
import com.longbridge.sams.admin.service.PermissionService;
import com.longbridge.sams.admin.service.SchoolService;
import com.longbridge.sams.model.Code;
import com.longbridge.sams.model.Permission;
import com.longbridge.sams.model.Role;
import com.longbridge.sams.model.School;
import com.longbridge.sams.model.UserType;
import com.longbridge.sams.utils.CustomBeanUtilsBean;
import com.longbridge.sams.utils.Messages;

@Controller
@RequestMapping("/admin/school/{schoolId}")
public class AdmSchoolRolesView {

	private static final Logger logger = LoggerFactory.getLogger(AdmSchoolRolesView.class);
	@Autowired
	private Messages message;

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private AdminRoleService roleService;

	@Autowired
	private PermissionService permissionService;

	@GetMapping("/roles")
	public String listRoles(@PathVariable Long schoolId, Model model) {
		return "admin/school/role/list";
	}

	@ModelAttribute(name = "school")
	public School getSchool(@PathVariable Long schoolId) {
		return schoolService.getSchool(schoolId);
	}

//	@ModelAttribute(name = "permissions")
//	public List<Role> getRoles(@PathVariable Long schoolId) {
//		return roleService.fetchAllRoles(schoolId);
//	}
	
	@ModelAttribute(name = "types")
	public UserType[] getTypes(@PathVariable Long schoolId) {
		return UserType.values();
	}

	@GetMapping("/role/new")
	public String newRole(Model model) {
		model.addAttribute("role", new Role());
//		Iterable<Permission> permissions = permissionService.getAllPermissions();
//		model.addAttribute("permissionList", permissions);
		return "admin/school/role/edit";
	}

	@GetMapping("/role/{rid}")
	public String editRole(@PathVariable Long rid, Model model) {
		Role role = roleService.getRole(rid);
		model.addAttribute("role", role);
		List<Permission> allOtherOptions = permissionService.getAllPermissionsNotInRole(role);
		model.addAttribute("permissionList", allOtherOptions);

		return "admin/school/role/edit";
	}

	@PostMapping("/role")
	public String createOrUpdateRole(@ModelAttribute("role") @Valid Role role, BindingResult result, WebRequest request,
			RedirectAttributes redirectAttributes, Model model, @PathVariable("schoolId") Long schoolId) {
		if (result.hasErrors()) {
			logger.warn("Error occurred creating role{}", result.toString());
			model.addAttribute("role", role);
			Iterable<Permission> permissions = null;
			if (role.getId() != null) {
				permissions = permissionService.getAllPermissionsNotInRole(role);
			} else {
//				permissions = permissionService.getAllPermissions();
			}
			model.addAttribute("permissionList", permissions);
			return "admin/school/roles/edit";
		}
		String response = "";
		try {
			String[] permissions = request.getParameterValues("permissions");
			if (permissions != null) {
				for (String perm : permissions) {
					Permission pdto = new Permission();
					pdto.setId(NumberUtils.toLong(perm));
					role.add(pdto);
				}
			}

			if (role.getId() != null) {
				roleService.modifyRole(schoolId, role);
				response = "role.update.success";

			} else {
				roleService.createRole(schoolId, role);
				response = "role.create.success";
			}

		} catch (ApplicationException e) {
			Iterable<Permission> permissions = null;
			result.reject(e.getMessage());
			if (role.getId() != null) {
				permissions = permissionService.getAllPermissionsNotInRole(role);
			} else {
//				permissions = permissionService.getAllPermissions();
			}
			model.addAttribute("permissionList", permissions);
			logger.error("Error occurred creating role{}", e);
			return "admin/school/role/edit";
		}
		redirectAttributes.addFlashAttribute("message", message.get(response, role.getName()));
		return "redirect:/admin/school/"+schoolId+"/roles";
	}
	
	

}
