package com.longbridge.sams.model;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


@Entity
@Table(
	    name="code", 
	    uniqueConstraints=
	        @UniqueConstraint(columnNames={"name", "type"})
	)
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



