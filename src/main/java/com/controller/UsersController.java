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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ResponseStructure;
import com.entity.Marks;
import com.entity.Users;
import com.service.UsersService;
import com.utilities.Grades;
import com.utilities.UpdateEmail;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;

@RestController
@RequestMapping("/student")
public class UsersController {

	@Autowired
	private UsersService usersService;

	@Operation(description = "To create a student record", summary = "Student will be saved into the Database")
	@ApiResponses(value = { @ApiResponse(description = "Create a student", responseCode = "201"),
			@ApiResponse(responseCode = "400", description = "Invalid input data"),
			@ApiResponse(responseCode = "409", description = "Unique constraint violation") })
	@PostMapping
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

	@Operation(description = "To retrieve a student data", summary = "Student data will be retrieved")
	@ApiResponses(value = { @ApiResponse(description = "retrieve an student", responseCode = "302"),
			@ApiResponse(responseCode = "404", description = "Student data not found") })
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Users>> getUser(@PathVariable int id) {
		return usersService.getUser(id);
	}

	@Operation(description = "To update student email", summary = "Student email will be updated")
	@ApiResponses(value = { @ApiResponse(description = "Update a student email", responseCode = "200"),
			@ApiResponse(description = "Student Id not found", responseCode = "404") })
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseStructure<Users>> updateUser(@RequestBody UpdateEmail email, @PathVariable int id) {
		return usersService.updateUser(email, id);
	}

	@Operation(description = "To delete a student record", summary = "Student record will be deleted")
	@ApiResponses(value = { @ApiResponse(description = "Delete a student record", responseCode = "200"),
			@ApiResponse(description = "Student Id not found", responseCode = "404") })
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable int id) {
		return usersService.deleteUser(id);
	}

	@Operation(description = "To update marks to the student", summary = "Student marks will be updated")
	@ApiResponses(value = { @ApiResponse(description = "Update a student marks", responseCode = "200"),
			@ApiResponse(description = "Student Id not found", responseCode = "404"),
			@ApiResponse(description = "Invalid input", responseCode = "400") })
	@PutMapping("/{id}")
	public ResponseEntity<ResponseStructure<Marks>> updateMarks(@PathVariable int id, @Valid @RequestBody Marks mark,
			BindingResult results) {

		if (results.hasErrors()) {
			String exception = "";
			for (FieldError error : results.getFieldErrors()) {
				exception += error.getDefaultMessage();
			}
			throw new ValidationException(exception);
		}

		return usersService.updateMarks(id, mark);
	}

	@Operation(description = "To get student grade", summary = "Student grade will be fetched")
	@ApiResponses(value = { @ApiResponse(description = "Fetch a student grade", responseCode = "302"),
			@ApiResponse(description = "Student Id not found", responseCode = "404") })
	@GetMapping("/grade/{id}")
	public ResponseEntity<ResponseStructure<Grades>> getGrade(@PathVariable int id) {
		return usersService.getGrade(id);
	}

	@Operation(description = "To fetch student aggregate marks", summary = "Student aggregate marks will be fetched")
	@ApiResponses(value = { @ApiResponse(description = "fetch student aggregate marks", responseCode = "302"),
			@ApiResponse(description = "Student Id not found", responseCode = "404") })
	@GetMapping("/agg/{id}")
	public ResponseEntity<ResponseStructure<Double>> getAggMarks(@PathVariable int id) {
		return usersService.getAggMarks(id);
	}
}
