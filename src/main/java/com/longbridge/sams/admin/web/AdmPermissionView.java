package com.longbridge.sams.admin.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.longbridge.sams.admin.service.PermissionService;
import com.longbridge.sams.model.Permission;
import com.longbridge.sams.utils.CustomBeanUtilsBean;
import com.longbridge.sams.utils.Messages;

@Controller
@RequestMapping("/admin/permission")
public class AdmPermissionView {

	private static final Logger logger = LoggerFactory.getLogger(AdmPermissionView.class);

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private Messages message;
	
	  	@GetMapping("/{id}")
	    public String editPermission(@PathVariable Long id, Model model){
	  		Permission permission = permissionService.get(id);
			model.addAttribute("permission",permission);
	        return "admin/permission/edit";
	    }
	  	
	  	@GetMapping("/new")
	    public String newPermission(Model model){
	  		model.addAttribute("permission",new Permission());
	        return "admin/permission/edit";
	    }
	  	
	    @GetMapping
	    public String listpermission(){
	        return "admin/permission/list";
	    }
	    
	    @PostMapping
		public String createOrUpdatePermission(@ModelAttribute("permission") @Valid Permission permission, BindingResult result,
				RedirectAttributes redirectAttributes) {
			logger.info("about to update/create permission  {} " + permission);

			String response = null;
			try {
				if (result.hasErrors()) {
					logger.warn("Error occurred creating Code{}", result.toString());
					return "admin/code/edit";
				}
				if (permission.getId() != null) {
					Permission permission2 = permissionService.get(permission.getId());
					CustomBeanUtilsBean.getInstance().copyProperties(permission2, permission);
					permission = permission2;
					permissionService.modify(permission);
					response = "permission.update.success";

				} else {
					permissionService.create(permission);
					response = "permission.create.success";
				}

			} catch (Exception ex) {
				result.reject(ex.getMessage());
				logger.error("Error occurred creating permission{}", ex);
				return "admin/permission/edit";
			}
			redirectAttributes.addFlashAttribute("message", message.get(response,permission.getName()));
			return "redirect:/admin/permission";
		}

}
