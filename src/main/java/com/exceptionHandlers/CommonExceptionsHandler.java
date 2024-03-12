package com.exceptionHandlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dto.ResponseStructure;
import com.exceptions.StudentNotFoundException;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;

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
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ResponseStructure<String>> validationExceptionHandler(ValidationException exceptions){
		responseStructure.setStatusCode(HttpStatus.BAD_REQUEST.value());
		responseStructure.setMessage(HttpStatus.BAD_REQUEST.getReasonPhrase());
		responseStructure.setData(exceptions.getMessage());
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> studentNotFoundException(StudentNotFoundException exceptions) {
		responseStructure.setStatusCode(HttpStatus.NOT_FOUND.value());
		responseStructure.setMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
		responseStructure.setData(exceptions.getMessage());

		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
	}

}
