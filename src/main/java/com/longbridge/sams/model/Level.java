package com.longbridge.sams.model;


import javax.persistence.Entity;

@Entity
public class Level extends AbstractSchoolEntity{
    private String name;
    private int ordinal;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrdinal() {
		return ordinal;
	}
	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

}
