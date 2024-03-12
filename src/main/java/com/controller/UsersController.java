package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ResponseStructure;
import com.entity.Marks;
import com.entity.Users;
import com.exceptions.MarksException;
import com.exceptions.ValidationException;
import com.service.UsersService;

import jakarta.validation.Valid;

@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;

	@PostMapping("/student")
	public ResponseEntity<ResponseStructure<Users>> save(@Valid @RequestBody Users user, BindingResult results) {

		if (results.hasErrors()) {
			String exception = "";
			for (FieldError error : results.getFieldErrors()) {
				exception += error.getDefaultMessage();
			}
			throw new ValidationException(exception);
		}

		return usersService.save(user);
	}

	
	@GetMapping("/student/{id}")
	public ResponseEntity<ResponseStructure<Users>> getUser(@PathVariable int id){
		return usersService.getUser(id);
	}
	
	@PutMapping("/student/update/{id}")
	public ResponseEntity<ResponseStructure<Users>> updateUser(@RequestBody String email,@PathVariable int id){
		return usersService.updateUser(email, id);
	}
	
	@DeleteMapping("/student/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int id){
		return usersService.deleteUser(id);
	}

	@PutMapping("/student/{id}")
	public ResponseEntity<ResponseStructure<Marks>> updateHalfYearlyMarks(@PathVariable int id,
			@Valid @RequestBody Marks mark, BindingResult results) {

		if (results.hasErrors()) {
			String exception = "";
			for (FieldError error : results.getFieldErrors()) {
				exception += error.getDefaultMessage();
			}
			throw new MarksException(exception);
		}

		return usersService.updateMarks(id, mark);
	}
}
