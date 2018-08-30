package com.longbridge.sams.model;

public enum Status {

	DISABLED("D"),ENABLED("E"),LOCKED("L");
	private final String status; 
	
	Status(String stat){
		this.status = stat;
	}
	
	@Override
	public String toString() {
		return status;
	}
}
