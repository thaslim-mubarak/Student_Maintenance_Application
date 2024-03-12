package com.exceptions;

public class MarksException extends RuntimeException {

	String message = "";

	public MarksException(String message) {
		super();
		this.message = message;
	}

	public MarksException() {
		super();
	}

	@Override
	public String getMessage() {
		return message;
	}

}
