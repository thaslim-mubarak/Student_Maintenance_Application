package com.exceptionHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dto.ResponseStructure;
import com.exceptions.ValidationException;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private ResponseStructure<String> responseStructure;
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ResponseStructure<String>> validationExceptionHandler(ValidationException exceptions){
		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		responseStructure.setData(exceptions.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.BAD_REQUEST);
	}
	
}
