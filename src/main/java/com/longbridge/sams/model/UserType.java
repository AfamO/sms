package com.longbridge.sams.model;

public enum UserType {
	
	ADMIN("Admin"),STAFF("Staff"),PARENT("Parent"),STUDENT("Student");
	private final String type; 
	UserType(String t){
		this.type = t;
	}
	
	@Override
	public String toString() {
		return type;
	}
}
