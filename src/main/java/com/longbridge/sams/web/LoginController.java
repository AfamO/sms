package com.longbridge.sams.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/")
public class LoginController {

	@GetMapping("/school/login")
	public String schoolLogin(WebRequest request) {

		Object attribute = request.getAttribute("sid",RequestAttributes.SCOPE_SESSION );
		if(attribute != null) {
			return "school/login2";
		}
		return "school/login";
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
