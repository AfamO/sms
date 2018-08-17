package com.longbridge.sams.admin.rest;

public class ResponseData {

	private Object data;
	private Object error;
	private Object message;
	
	
	public ResponseData(Object data) {
		super();
		this.data = data;
	}
	
	
	public ResponseData() {
		// TODO Auto-generated constructor stub
	}


	public Object getError() {
		return error;
	}
	public void setError(Object error) {
		this.error = error;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	
	
}
