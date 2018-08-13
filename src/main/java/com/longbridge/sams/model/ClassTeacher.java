package com.longbridge.sams.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ClassTeacher extends AbstractSchoolEntity{

	@ManyToOne
	private Staff teacher;
	
	private String designation;
	public Staff getTeacher() {
		return teacher;
	}
	public void setTeacher(Staff teacher) {
		this.teacher = teacher;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	
}
