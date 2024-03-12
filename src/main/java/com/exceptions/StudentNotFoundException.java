package com.exceptions;

public class StudentNotFoundException extends RuntimeException {

	String message = "";

	public StudentNotFoundException() {
		super();
	}

	public StudentNotFoundException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

}
