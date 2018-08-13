package com.longbridge.sams.model;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
public class Qualification extends AbstractEntity {

    private String institutionName;
    private String institutionType;
    private String qualification;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "${date.format}")
    private Date aquired;


    public Qualification() {
    }


	public String getInstitutionName() {
		return institutionName;
	}


	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}


	public String getInstitutionType() {
		return institutionType;
	}


	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}


	public String getQualification() {
		return qualification;
	}


	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public Date getAquired() {
		return aquired;
	}


	public void setAquired(Date aquired) {
		this.aquired = aquired;
	}

}
