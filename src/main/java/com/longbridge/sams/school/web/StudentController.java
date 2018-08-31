package com.longbridge.sams.school.web;


import com.longbridge.sams.model.Student;
import com.longbridge.sams.school.service.StudentService;
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
@RequestMapping("/school/student")
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private Messages message;

    @Autowired
    private StudentService studentService;

    @GetMapping("/new")
    public String newStudent(Model model) {
        model.addAttribute("student",new Student());
        return "school/Student/edit";
    }

    @PostMapping
    public String createOrUpdateSchool(@ModelAttribute("school") @Valid Student student, BindingResult result,
                                       RedirectAttributes redirectAttributes) {
        logger.info("studentData received is {} " + student);

        String response = null;
        try {
            if (result.hasErrors()) {
                logger.warn("Error occurred creating Student{}", result.toString());
                return "school/student/edit";
            }
            if (student.getId() != null) {
                Student std1 = studentService.getStudent(student.getId());
                CustomBeanUtilsBean.getInstance().copyProperties(std1, student);
                student = std1;
                studentService.updateStudent(student);
                response = "student.update.success";


            } else {
                studentService.createStudent(student);
                response = "student.create.success";
            }

        } catch (Exception ex) {
            result.reject(ex.getMessage());
            logger.error("Error occurred creating Student{}", ex);
            return "school/student/edit";
        }
        redirectAttributes.addFlashAttribute("message", message.get(response,student.getAdmissionNumber()));
        return "redirect:/school/student";
    }
    @GetMapping
    public String listStudent(){
        return "school/Student/list";
    }

}
