package com.longbridge.sams.aop;

import javax.persistence.EntityManager;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.longbridge.sams.SchoolContext;

@Aspect
@Component
public class SchoolTenantAspect {

	@Autowired
	EntityManager em;

	@Pointcut("execution(* com.longbridge.sams.school.service..* (..))")
	public void inSchoolServiceLayer() {
	}
	
	@Pointcut("execution(* com.longbridge.sams.pub.service..*(..))")
	public void inPubServiceLayer() {
	}
	
	@Before("inSchoolServiceLayer() || inPubServiceLayer()")
	public void aroundExecution(JoinPoint pjp) throws Throwable {
		org.hibernate.Filter filter = em.unwrap(Session.class).enableFilter("schoolFilter");
		filter.setParameter("sid", SchoolContext.getCurrentTenant());
		filter.validate();
	}
}