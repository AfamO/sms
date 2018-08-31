package com.longbridge.sams.school.web;

import com.longbridge.sams.admin.web.AdmSchoolUsersView;
import com.longbridge.sams.model.Qualification;
import com.longbridge.sams.model.Staff;
import com.longbridge.sams.school.service.QualificationService;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/school/staff/{staffId}")
public class QualificationController {

    private static final Logger logger = LoggerFactory.getLogger(QualificationController.class);

    @Autowired
    private Messages messages;

    @Autowired
    private QualificationService qualificationService;


    @Autowired
    private StaffService staffService;

    @GetMapping("/qualifictions")
    public String listQualification(@PathVariable Long staffId,Model model){

        return "school/qualification/list";
    }



    @GetMapping("/new")
    public String newQualification(Model model){
        model.addAttribute("qualification",new Qualification());
        return "school/qualification/edit";
    }

    @GetMapping("/edit/{id}")
    public String editQualification(@PathVariable Long id, Model model){
        Qualification qualification = qualificationService.getQualification(id);

        model.addAttribute("qualification",qualification);
        return "school/qualification/edit";
    }

    @PostMapping
    public String createOrUpdateSchool(@ModelAttribute("school") @Valid Qualification qualification, BindingResult result,
                                       RedirectAttributes redirectAttributes) {
        logger.info("schoolDTO received is {} " + qualification);

        String response = null;
        try {
            if (result.hasErrors()) {
                logger.warn("Error occurred creating School{}", result.toString());
                return "school/qualfication/edit";
            }
            if (qualification.getId() != null) {
                Qualification sch2 = qualificationService.getQualification(qualification.getId());
                CustomBeanUtilsBean.getInstance().copyProperties(sch2, qualification);
                qualification = sch2;
                qualificationService.updateQualification(qualification);
                response = "school.update.success";

            } else {
//                List<Qualification> qualificationList = new ArrayList<>();
//                Staff staff = staffService.getStaff();
                qualificationService.createQualif(qualification);
                response = "qualification.create.success";
            }

        } catch (Exception ex) {
            result.reject(ex.getMessage());
            logger.error("Error occurred creating School{}", ex);
            return "school/qualification/edit";
        }
        redirectAttributes.addFlashAttribute("message", messages.get(response));
        return "redirect:/school/qualification";
    }

}
