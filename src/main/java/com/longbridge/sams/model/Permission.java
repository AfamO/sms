package com.longbridge.sams.model;


import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Permission extends AbstractEntity {
	
	
	/**
	 * 
	 */

	public Permission(String name, String category, String description) {
		super();
		this.name = name;
		this.category = category;
		this.description = description;
	}
	
	public Permission(String name, String category, String description, UserType type) {
		super();
		this.name = name;
		this.category = category;
		this.description = description;
		this.type = type;
	}

	public Permission() {
	}

	@NotEmpty
	private String name;
	
	private UserType type;
	
	private String category;
	@NotEmpty
	private String description;
	@ManyToMany(mappedBy="permissions")
	private Set<Role>  roles;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	
	@JsonIgnore
	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	
	
	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Permission [id=" + getId() + ", name=" + name + ", category=" + category + ", description=" + description + "]";
	}
	@Override
	public List<String> getDefaultSearchFields() {
			return Arrays.asList("description", "name","category");
	}

}
