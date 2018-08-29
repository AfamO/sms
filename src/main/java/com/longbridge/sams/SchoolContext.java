package com.longbridge.sams;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.longbridge.sams.security.service.UserDetailsImpl;

public class SchoolContext {

  private static ThreadLocal<Long> currentTenant = new ThreadLocal<>();

  public static Long getCurrentTenant() {
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  if(auth == null)
		  return null;
	  UserDetailsImpl user = (UserDetailsImpl)auth.getPrincipal();
	  return user.getSid();
  }


  public static void clear() {
    currentTenant.set(null);
  }

}