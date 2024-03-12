package com.exceptions;

public class StudentNotFoundException extends RuntimeException {
	String exception = "";

	public StudentNotFoundException(String exception) {
		this.exception = exception;
	}

	public StudentNotFoundException() {
		super();
	}

	@Override
	public String getMessage() {

		return exception;
	}

}
