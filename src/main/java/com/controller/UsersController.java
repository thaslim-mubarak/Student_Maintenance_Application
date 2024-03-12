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
import com.utilities.Grades;
import com.utilities.UpdateEmail;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@Operation(description = "to create student",summary = "student will be saved")
	@ApiResponses(value = @ApiResponse(description = "create an student", responseCode = "201"))
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

	
	@Operation(description = "to retrieve student",summary = "student will be retrieved")
	@ApiResponses(value = @ApiResponse(description = "retrive an student", responseCode = "200"))
	@GetMapping("/student/{id}")
	public ResponseEntity<ResponseStructure<Users>> getUser(@PathVariable int id){
		return usersService.getUser(id);
	}
	
	@Operation(description = "to update student",summary = "student will be updated")
	@ApiResponses(value = @ApiResponse(description = "update an student", responseCode = "200"))
	@PutMapping("/student/update/{id}/email/{email}")
	public ResponseEntity<ResponseStructure<Users>> updateUser(@RequestBody UpdateEmail email,@PathVariable int id){
		return usersService.updateUser(email, id);
	}
	
	@Operation(description = "to delete an student",summary = "student will be deleted")
	@ApiResponses(value = @ApiResponse(description = "delete an student", responseCode = "200"))
	@DeleteMapping("/student/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int id){
		return usersService.deleteUser(id);
	}

	
	@Operation(description = "to update marks",summary = "student marks will be updated")
	@ApiResponses(value = @ApiResponse(description = "update an student marks", responseCode = "200"))
	@PutMapping("/student/{id}")
	public ResponseEntity<ResponseStructure<Marks>> updateMarks(@PathVariable int id,
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
	
	@Operation(description = "to get student grade",summary = "student grade will be fetched")
	@ApiResponses(value = @ApiResponse(description = "fetch an student grade", responseCode = "200"))
	@GetMapping("/student/grade/{id}")
	public ResponseEntity<ResponseStructure<Grades>> getGrade(@PathVariable int id){
		return usersService.getGrade(id);
	}
	
	@Operation(description = "to fetch student aggregate marks",summary = "student aggregate marks will be fetched")
	@ApiResponses(value = @ApiResponse(description = "fetch student aggregate marks", responseCode = "200"))
	@GetMapping("/student/agg/{id}")
	public ResponseEntity<ResponseStructure<Double>> getAggMarks(@PathVariable int id){
		return usersService.getAggMarks(id);
	}
}
