package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dao.UsersDao;
import com.dto.ResponseStructure;
import com.entity.Users;

@Service
public class UsersService {
	
	@Autowired
	private UsersDao users_dao;
	
	@Autowired
	private ResponseStructure<Users> responseStructure;
	
	public ResponseEntity<ResponseStructure<Users>> save(Users user){
		
		users_dao.save(user);
		
		responseStructure.setStatusCode(HttpStatus.CREATED.value());
		responseStructure.setMessage(HttpStatus.CREATED.getReasonPhrase());
		responseStructure.setData(user);
		
		return new ResponseEntity<ResponseStructure<Users>>(responseStructure, HttpStatus.CREATED);
	}

}
