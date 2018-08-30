package com.longbridge.sams.admin.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.longbridge.sams.admin.service.AdminRoleService;
import com.longbridge.sams.admin.service.SchoolService;
import com.longbridge.sams.admin.service.UserService;
import com.longbridge.sams.model.Role;
import com.longbridge.sams.model.School;
import com.longbridge.sams.model.User;
import com.longbridge.sams.utils.CustomBeanUtilsBean;
import com.longbridge.sams.utils.Messages;

@Controller
@RequestMapping("/admin/school/{schoolId}")
public class AdmSchoolUsersView {

	private static final Logger logger = LoggerFactory.getLogger(AdmSchoolUsersView.class);
	@Autowired
	private Messages message;

	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminRoleService roleService;

	@GetMapping("/users")
	public String listUsers(@PathVariable Long schoolId, Model model) {
		return "admin/user/list";
	}

	@ModelAttribute(name = "school")
	public School getSchool(@PathVariable Long schoolId){
		return schoolService.getSchool(schoolId);
	}
	
	@ModelAttribute(name = "roles")
	public List<Role> getRoles(@PathVariable Long schoolId) {
		return roleService.fetchAllRoles(schoolId);
	}

	@GetMapping("/user/new")
	public String newUser(Model model) {
		model.addAttribute("user", new User());
		return "admin/user/edit";
	}


	@GetMapping("/user/{uid}")
	public String editUser(@PathVariable Long uid,Model model) {
		User user = userService.getUser(uid);
		model.addAttribute("user", user);
		return "admin/user/edit";
	}
	
	@PostMapping("/user")
	public String createOrUpdateRole(@ModelAttribute("user") @Valid User user, BindingResult result,
			RedirectAttributes redirectAttributes, Model model, @PathVariable("schoolId") Long schoolId) {
		if (result.hasErrors()) {
			logger.warn("Error occurred creating role{}", result.toString());
			model.addAttribute("user", user);
			return "admin/roles/edit";
		}
		String response = "";
		try {
			if (user.getId() != null) {
				User user2 = userService.getUser(user.getId());
				CustomBeanUtilsBean.getInstance().copyProperties(user2, user);
				user = user2;
				userService.updateUser(user);
				response = "user.update.success";
			} else {
				userService.createUser(schoolId,user);
				response = "user.create.success";
			}

		} catch (Exception e) {
			result.reject(e.getMessage());
			logger.error("Error occurred creating/Updating User{}", e);
			return "admin/user/edit";
		}
		redirectAttributes.addFlashAttribute("message", message.get(response, user.getLoginId()));
		return "redirect:/admin/school/"+schoolId+"/users";
	}

	
}
