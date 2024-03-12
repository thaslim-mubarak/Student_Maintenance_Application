package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.UsersDao;
import com.dto.ResponseStructure;
import com.entity.Marks;
import com.entity.Users;
import com.utilities.Grades;

@Service
public class UsersService {

	@Autowired
	private UsersDao users_dao;

	@Autowired
	private ResponseStructure<Users> responseStructure;
	
	@Autowired
	private ResponseStructure<Marks> responseStructureMarks;
	
	@Autowired
	private ResponseStructure<Double> responseStructureDouble;
	
	@Autowired
	private ResponseStructure<Grades> responseStructureGrade;

	public ResponseEntity<ResponseStructure<Users>> save(Users user) {

		users_dao.save(user);

		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage(HttpStatus.CREATED.getReasonPhrase());
		responseStructure.setData(user);

		return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Users>> getUser(int id){
		Users u=users_dao.getUser(id);
		
		responseStructure.setStatusCode(HttpStatus.FOUND.value());
		responseStructure.setMessage(HttpStatus.FOUND.getReasonPhrase());
		responseStructure.setData(u);
		
		return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.FOUND);
	}
	
	public ResponseEntity<ResponseStructure<Users>> updateUser(String email,int id){
		Users u=users_dao.updateUser(email, id);
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage(HttpStatus.OK.getReasonPhrase());
		responseStructure.setData(u);
		
		return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteUser(int id){
		users_dao.removeUser(id);
		
		ResponseStructure<String> responseStructure=  new ResponseStructure<String>();
		
		responseStructure.setStatusCode(HttpStatus.OK.value());
		responseStructure.setMessage(HttpStatus.OK.getReasonPhrase());
		responseStructure.setData(id+" is deleted");
		
		return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.OK);
		
	}

	public ResponseEntity<ResponseStructure<Marks>> updateMarks(int id, Marks mark) {

		users_dao.updateMarks(id, mark);

		responseStructureMarks.setStatusCode(HttpStatus.CREATED.value());
		responseStructureMarks.setMessage(HttpStatus.CREATED.getReasonPhrase());
		responseStructureMarks.setData(mark);

		return new ResponseEntity<ResponseStructure<Marks>>(responseStructureMarks, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<Double>> getAggMarks(int id) {

		responseStructureDouble.setStatusCode(HttpStatus.FOUND.value());
		responseStructureDouble.setMessage(HttpStatus.FOUND.getReasonPhrase());
		responseStructureDouble.setData(users_dao.getAggMarks(id));

		return new ResponseEntity<ResponseStructure<Double>>(responseStructureDouble, HttpStatus.FOUND);
	}
	
	public ResponseEntity<ResponseStructure<Grades>> getGrade(int id) {

		responseStructureGrade.setStatusCode(HttpStatus.FOUND.value());
		responseStructureGrade.setMessage(HttpStatus.FOUND.getReasonPhrase());
		responseStructureGrade.setData(users_dao.getGrade(id));

		return new ResponseEntity<ResponseStructure<Grades>>(responseStructureGrade, HttpStatus.FOUND);
	}
}
