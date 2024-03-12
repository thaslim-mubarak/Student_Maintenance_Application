package com.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.Marks;
import com.entity.Users;
import com.exceptions.MarksException;
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
		return user;
	}
	
	public Users updateMarks(int id, Marks mark) {
		Optional<Users> opt_user = usersRepository.findById(id);
		if(opt_user.isPresent()) {
			Users user = opt_user.get();
			List<Marks> mark_list = user.getMarks();
			if(mark_list.size() <= 3) {
				mark_list.add(mark);
				user.setMarks(mark_list);
				user.setGrade(Update.setGrade(user));
				return usersRepository.save(user);
			}
			else {
				throw new MarksException("Three marks are allowed for a student");
			}
		}
		else {
			throw new StudentNotFoundException("Student with the ID: "+id+" not found");
		}
	}

}
