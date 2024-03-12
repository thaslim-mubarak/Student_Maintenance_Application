package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.Marks;
import com.entity.Users;
import com.exceptions.MarksException;
import com.exceptions.StudentNotFoundException;
import com.repository.UsersRepository;
import com.utilities.Grades;
import com.utilities.GradeSetter;
import com.utilities.UpdateEmail;

@Repository
public class UsersDao {

	@Autowired
	private UsersRepository usersRepository;

	public Users save(Users user) {
		if(user.getMarks().size() == 0 || user.getMarks() == null) {
			user.setMarks(new ArrayList<Marks>());
		}
		user.setGrade(GradeSetter.setGrade(user));
		usersRepository.save(user);
		return user;
	}

	public Users updateMarks(int id, Marks mark) {
		Optional<Users> opt_user = usersRepository.findById(id);
		if (opt_user.isPresent()) {
			Users user = opt_user.get();
			List<Marks> mark_list = user.getMarks();
			if (mark_list.size() <= 3) {
				mark_list.add(mark);
				user.setMarks(mark_list);
				user.setGrade(GradeSetter.setGrade(user));
				return usersRepository.save(user);
			} else {
				throw new MarksException("Three marks are allowed for a student");
			}
		} else {
			throw new StudentNotFoundException("Student with the ID: " + id + " not found");
		}
	}

	public Users getUser(int id) {
		Optional<Users> opt_users = usersRepository.findById(id);
		if (opt_users.isPresent()) {
			return opt_users.get();
		} else {
			throw new StudentNotFoundException("Student not found");
		}
	}

	public Users updateUser(UpdateEmail email, int id) {
		Optional<Users> opt_users = usersRepository.findById(id);
		if (opt_users.isPresent()) {
			Users u = opt_users.get();
			u.setEmail(email.getEmail());
			usersRepository.save(u);
			return u;
		} else {
			throw new StudentNotFoundException("Student not found");
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
			throw new StudentNotFoundException("Student not found");
		}
	}
	
	public Grades getGrade(int id) {
		Optional<Users> opt_users=usersRepository.findById(id);
		if(opt_users.isPresent()) {
			Users u=opt_users.get();
			return u.getGrade();
		}
		else {
			throw new StudentNotFoundException("Student not found");
		}
	}
	
	public double getAggMarks(int id) {
		Optional<Users> opt_users=usersRepository.findById(id);
		if(opt_users.isPresent()) {
			Users u=opt_users.get();
			List<Marks> marks = u.getMarks();
			double total = 0;
			for(Marks m : marks) {
				total += m.getLanguage()+m.getEnglish()+m.getMaths()+m.getScience()+m.getSs();
			}
			return total/150;
		}
		else {
			throw new StudentNotFoundException("Student not found");
		}
	}
	
	

}
