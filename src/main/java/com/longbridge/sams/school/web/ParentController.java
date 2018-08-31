package com.longbridge.sams.school.web;


import com.longbridge.sams.admin.web.AdmSchoolView;
import com.longbridge.sams.model.Parent;
import com.longbridge.sams.school.service.ParentService;
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
import java.util.Optional;

@Controller
@RequestMapping("/school/parent")
class  ParentController{

    @Autowired
    private Messages message;

    private static final Logger logger = LoggerFactory.getLogger(AdmSchoolView.class);

    @Autowired
    private ParentService parentService;


    @GetMapping("/{id}/edit")
    public String editParent(@PathVariable Long id , Model model){
        Optional<Parent> parent = parentService.getParent(id);

        model.addAttribute("parent",parent);

        return "pages/parent/add";

    }

    @PostMapping("/create")
    public String createOrUpdateParent(@ModelAttribute("parent") @Valid Parent parent , BindingResult result, RedirectAttributes redirectAttributes){

        String response = null;

        try{
            if(result.hasErrors()){
                logger.warn("Error occured creating Parent{}", result.toString());
                return "page/parent/add";
            }

            if (parent.getId() != null){
                parentService.modify(parent);
                response = "parent.update.success";
            }else {
                System.out.println("I got here before saving");

                parentService.create(parent);
                response = "parent.update.success";

                System.out.println("");
                System.out.println("I got here after saving");
            }

        }catch(Exception ex){
            result.reject(ex.getMessage());
            logger.error("Error occurred creating Parent{}", ex);
            return "pages/parent/add";
        }
        redirectAttributes.addFlashAttribute("message", message.get("parent.create.success"));
        return "redirect:/school/parent/list";

    }

    @GetMapping("/new")
    public String newParent(Model model){

        model.addAttribute("parent", new Parent());

        return "pages/parent/add";
    }

    @GetMapping("/list")
    public String listParent(){
        return "pages/parent/list";
    }

    @GetMapping("/{id}/info")
    public String ParentInfo(@PathVariable Long id, Model model){

        Optional<Parent> parent = parentService.getParent(id);

        model.addAttribute("parent",parent);

        return  "pages/parent/info";
    }
}