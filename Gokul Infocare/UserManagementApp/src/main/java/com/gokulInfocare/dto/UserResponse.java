package com.gokulInfocare.dto;

public class UserResponse {
	
	private String message;
	
	public UserResponse (String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "UserResponse [message=" + message + "]";
	}
	
	

}
