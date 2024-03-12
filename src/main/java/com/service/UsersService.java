package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.UsersDao;
import com.dto.ResponseStructure;
import com.entity.Marks;
import com.entity.Users;

@Service
public class UsersService {

	@Autowired
	private UsersDao users_dao;

	@Autowired
	private ResponseStructure<Users> responseStructure;
	
	@Autowired
	private ResponseStructure<Marks> responseStructureMarks;

	public ResponseEntity<ResponseStructure<Users>> save(Users user) {

		users_dao.save(user);

		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage(HttpStatus.CREATED.getReasonPhrase());
		responseStructure.setData(user);

		return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Marks>> updateMarks(int id, Marks mark) {

		users_dao.updateMarks(id, mark);

		responseStructureMarks.setStatusCode(HttpStatus.CREATED.value());
		responseStructureMarks.setMessage(HttpStatus.CREATED.getReasonPhrase());
		responseStructureMarks.setData(mark);

		return new ResponseEntity<ResponseStructure<Marks>>(responseStructureMarks, HttpStatus.CREATED);
	}

}
