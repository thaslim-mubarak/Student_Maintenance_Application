package com.entity;

import java.util.ArrayList;
import java.util.List;

import com.utilities.Grades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "students_record")
@Data
@RequiredArgsConstructor
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int stud_id;
	
	@Size(min = 3, max = 30, message = "Name should be between 3 and 30 characters")
	private String name;
	
	@Column(unique = true, updatable = true)
	@Email(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,4}$", message = "")
	private String email;
	
	@Enumerated(EnumType.STRING)
	private Grades grade;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Marks> marks = new ArrayList<Marks>();
	
}
