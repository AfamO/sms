package com.longbridge.sams.admin.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.longbridge.sams.admin.service.SchoolService;
import com.longbridge.sams.model.School;
import com.longbridge.sams.utils.CustomBeanUtilsBean;
import com.longbridge.sams.utils.Messages;

@Controller
@RequestMapping("/admin/school")
public class AdmSchoolView {

	private static final Logger logger = LoggerFactory.getLogger(AdmSchoolView.class);
	@Autowired
	private Messages message;

	@Autowired
	private SchoolService schoolService;

	@GetMapping("/{id}/edit")
	public String editSchool(@PathVariable Long id, Model model) {
		School school = schoolService.getSchool(id);
		model.addAttribute("school", school);
//		model.addAttribute("logo", );
//		model.addAttribute("banner", school);
		return "admin/school/edit";
	}

	@PostMapping
	public String createOrUpdateSchool(@ModelAttribute("school") @Valid School school, BindingResult result,
			RedirectAttributes redirectAttributes, @RequestParam("_logo") MultipartFile logo,@RequestParam("_banner") MultipartFile banner) {
		logger.info("schoolDTO received is {} " + school);

		String response = null;
		try {
			if (result.hasErrors()) {
				logger.warn("Error occurred creating School{}", result.toString());
				return "admin/school/edit";
			}
			if (school.getId() != null) {
				School sch2 = schoolService.getSchool(school.getId());
				CustomBeanUtilsBean.getInstance().copyProperties(sch2, school);
				school = sch2;
				schoolService.update(school);
				response = "school.update.success";

			} else {
				schoolService.create(school, true);
				response = "school.create.success";
			}

		} catch (Exception ex) {
			result.reject(ex.getMessage());
			logger.error("Error occurred creating School{}", ex);
			return "admin/school/edit";
		}
		redirectAttributes.addFlashAttribute("message", message.get(response,school.getCode()));
		return "redirect:/admin/school";
	}

	@GetMapping("/new")
	public String newSchool(Model model) {
		model.addAttribute("school", new School());
		return "admin/school/edit";
	}

	@GetMapping
	public String listschool() {
		return "admin/school/list";
	}

	@GetMapping("/info")
	public String schoolInfo() {
		return "admin/school/info";
	}
}
