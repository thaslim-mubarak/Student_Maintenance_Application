package com.exceptions;

public class ValidationException extends RuntimeException {
	String exception = "";

	public ValidationException(String exception) {

		this.exception = exception;
	}

	public ValidationException() {
		super();
	}

	@Override
	public String getMessage() {
		return exception;
	}
}
