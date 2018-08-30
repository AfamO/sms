package com.longbridge.sams.model;

import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(uniqueConstraints={ @UniqueConstraint(columnNames = {"name" , "type"})})
public class Code extends AbstractEntity  {

	@NotEmpty
	private String name;
	@NotEmpty
	private String description;
	@NotEmpty
	private String type;
	 

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	@Override
	public String toString() {
		return "Code [name=" + name + ", description=" + description + ", type=" + type + ", getId()=" + getId() + "]";
	}
	
	@Override 
	public List<String> getDefaultSearchFields() {
		return Arrays.asList("name", "description","type");
	}
	

}



