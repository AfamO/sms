package com.longbridge.sams.model;

public enum UserStatus {

	DISABLED("D"),ENABLED("E"),LOCKED("L");
	private final String status; 
	
	UserStatus(String stat){
		this.status = stat;
	}
	
	@Override
	public String toString() {
		return status;
	}
}
