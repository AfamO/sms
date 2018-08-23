package com.longbridge.sams.web;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/")
public class LoginController {

	@GetMapping("/school/login")
	public String schoolLogin(HttpSession session) {
		Object attribute = session.getAttribute("school" );
		if(attribute == null)
			return "school/login";
		
		return "school/login2";
	};
	
	@GetMapping("/pub/login")
	public String pubLogin(WebRequest request) {
		Object attribute = request.getAttribute("sid",RequestAttributes.SCOPE_SESSION );
		if(attribute != null) {
			return "pub/login2";
		}
		return "pub/login";
	};
	
	@GetMapping("/admin/login")
	public String adminLogin() {
		return "admin/login";
	};
}
