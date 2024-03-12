package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ResponseStructure;
import com.entity.Users;
import com.exceptions.ValidationException;
import com.service.UsersService;

import jakarta.validation.Valid;

@RestController
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@PostMapping("/student")
	public ResponseEntity<ResponseStructure<Users>> save(@Valid @RequestBody Users user, BindingResult results){
		
		if(results.hasErrors()) {
			String exception = "";
			for(FieldError error : results.getFieldErrors()) {
				exception += error.getDefaultMessage();
			}
			throw new ValidationException(exception);
		}
		
		
		return usersService.save(user);
	}
	
	

}
