package com.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.Users;
import com.exceptionHandlers.StudentNotFoundExceptionHandler;
import com.exceptions.StudentNotFoundException;
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
	
	public Users getUser(int id) {
		Optional<Users> opt_users=usersRepository.findById(id);
		if(opt_users.isPresent()) {
			return opt_users.get();
		}
		else {
			throw new StudentNotFoundException("student not found");
		}
	}
	
	public Users updateUser(String email,int id) {
		Optional<Users> opt_users=usersRepository.findById(id);
		if(opt_users.isPresent()) {
			Users u=opt_users.get();
			u.setEmail(email);
			usersRepository.save(u);
			return u;
		}
		else {
			throw new StudentNotFoundException("student not found");
		}
	}
	
	public String removeUser(int id) {
		Optional<Users> opt_users=usersRepository.findById(id);
		if(opt_users.isPresent()) {
			Users u=opt_users.get();
			usersRepository.delete(u);
			return "user got deleted";
		}
		else {
			throw new StudentNotFoundException("student not found");
		}
	}

}
