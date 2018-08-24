package com.longbridge.sams.data.dto;

public class FieldError {

	private String field;
	
	private String message;

	
	public FieldError(String field, String message) {
		super();
		this.field = field;
		this.message = message;
	}

	public FieldError() {
	}
	
	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
