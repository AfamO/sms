package com.longbridge.sams.security.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

public class SchoolAuthenticationFilter extends GenericFilterBean {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		if(request instanceof HttpServletRequest) {
            HttpServletRequest r = (HttpServletRequest)request;
            String schoolId = r.getParameter("sid");
            logger.debug(String.format("Getting Sid %s context",schoolId));
            if(StringUtils.isNotBlank(schoolId))
            	r.getSession().setAttribute("sid", schoolId);
        }
        chain.doFilter(request, response);
	}

}
