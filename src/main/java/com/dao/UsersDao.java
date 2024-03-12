package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.Users;
import com.repository.MarksRepository;
import com.repository.UsersRepository;
import com.utilities.Update;

@Repository
public class UsersDao {
	
	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private MarksRepository marksRepository;
	
	public Users save(Users user) {
		user.setGrade(Update.setGrade(user));
		usersRepository.save(user);
		marksRepository.save(user.getMarks().get(0));
		return user;
	}

}
