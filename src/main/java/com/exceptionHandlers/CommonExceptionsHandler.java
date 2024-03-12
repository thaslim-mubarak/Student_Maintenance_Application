package com.exceptionHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dto.ResponseStructure;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class CommonExceptionsHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private ResponseStructure<String> responseStructure;
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ResponseStructure<String>> commonExceptionHandler(DataIntegrityViolationException exceptions){
		responseStructure.setStatusCode(HttpStatus.CONFLICT.value());
		responseStructure.setMessage(HttpStatus.CONFLICT.getReasonPhrase());
		responseStructure.setData("Unique value of a field being violated");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ResponseStructure<String>> commonExceptionHandler(ConstraintViolationException exceptions){
		responseStructure.setStatusCode(HttpStatus.FAILED_DEPENDENCY.value());
		responseStructure.setMessage(HttpStatus.FAILED_DEPENDENCY.getReasonPhrase());
		responseStructure.setData("Range of a field being violated");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.FAILED_DEPENDENCY);
	}
	
	

}
