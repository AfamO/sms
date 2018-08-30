package com.longbridge.sams.admin.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.longbridge.sams.admin.service.CodeService;
import com.longbridge.sams.admin.service.implementation.CodeServiceImpl;
import com.longbridge.sams.model.Code;
import com.longbridge.sams.utils.CustomBeanUtilsBean;
import com.longbridge.sams.utils.Messages;

@Controller
@RequestMapping("/admin/code")
public class AdmCodeView {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private CodeService codeService;
	
	
	@Autowired
	private Messages message;
	
	@GetMapping("/{id}")
    public String editCode(@PathVariable("id")Long id, Model model){
		Code code = codeService.getCode(id);
		model.addAttribute("code",code);
        return "admin/code/edit";
    }
  	
	
	@GetMapping("/new")
    public String newCode( Model model){
		model.addAttribute("code",new Code());
        return "admin/code/edit";
    }
	
	@GetMapping("{type}/new")
    public String newCodeOfType(@PathVariable("type")String type, Model model){
		Code code = new Code();
		code.setType(type);
		model.addAttribute("code",code);
        return "admin/code/edit";
    }
	
    @GetMapping("/type")
    public String listTypes(){

        return "admin/code/list-type";
    }
    
    @GetMapping("/type/{codetype}")
    public String listcode(@PathVariable("codetype") String codeType, Model model){
    	model.addAttribute("codetype",codeType);
        return "admin/code/list-type-code";
    }
    
    @PostMapping
	public String createOrUpdateCode(@ModelAttribute("code") @Valid Code code, BindingResult result,
			RedirectAttributes redirectAttributes) {
		logger.info("about to update/create code  {} " + code);

		String response = null;
		try {
			if (result.hasErrors()) {
				logger.warn("Error occurred creating Code{}", result.toString());
				return "admin/code/edit";
			}
			if (code.getId() != null) {
				Code code2 = codeService.getCode(code.getId());
				CustomBeanUtilsBean.getInstance().copyProperties(code2, code);
				code = code2;
				codeService.modify(code);
				response = "code.update.success";

			} else {
				codeService.add(code);
				response = "code.create.success";
			}

		} catch (Exception ex) {
			result.reject(ex.getMessage());
			logger.error("Error occurred creating Code{}", ex);
			return "admin/code/edit";
		}
		redirectAttributes.addFlashAttribute("message", message.get(response,code.getName()));
		return "redirect:/admin/code/type/"+code.getType();
	}


}
