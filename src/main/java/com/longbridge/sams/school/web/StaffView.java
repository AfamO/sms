package com.longbridge.sams.school.web;

import com.longbridge.sams.model.Staff;
import com.longbridge.sams.school.service.StaffService;
import com.longbridge.sams.utils.CustomBeanUtilsBean;
import com.longbridge.sams.utils.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/school/staff")
public class StaffView {

	private  final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private Messages messages;

	@Autowired
    private StaffService staffService;


  	
  	@GetMapping("/new")
    public String newStaff(Model model){
        model.addAttribute("staff", new Staff());
  	    return "school/staff/edit";
    }
  	
    @GetMapping
    public String liststaff(){

        return "school/staff/list";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Staff staff = staffService.getStaff(id);
        model.addAttribute("staff", staff);
        return "school/staff/edit";
    }

    @GetMapping("/info")
    public String schoolInfo(){
        return "/school/staff/info";
    }

    @PostMapping
    public String createOrUpdateSchool(@ModelAttribute("staff") @Valid Staff staff, BindingResult result,RedirectAttributes redirectAttributes){
        logger.info("staffDTO received is {} " + staff);

        String response = null;

        try {
            if (result.hasErrors()) {
                logger.warn("Error occurred creating Staff{}", result.toString());
                return "/school/staff/edit";
            }
            if (staff.getId() != null) {
                Staff staff2 = staffService.getStaff(staff.getId());
                CustomBeanUtilsBean.getInstance().copyProperties(staff2, staff);
                staff = staff2;
                staffService.updateStaff(staff);
                response = "school.update.success";

            } else {
                staffService.createStaff(staff);
                response = "staff.create.success";
            }

        } catch (Exception ex) {
            result.reject(ex.getMessage());
            logger.error("Error occurred creating School{}", ex);
            return "/school/staff/edit";
        }
        redirectAttributes.addFlashAttribute("message", messages.get(response));
        return "redirect:/school/staff";
    }
}
