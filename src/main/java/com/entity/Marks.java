package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Marks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int marks_id;
	
	@PositiveOrZero
	@Max(value = 100, message = "Invalid marks")
	private double language;
	
	@PositiveOrZero
	@Max(value = 100, message = "Invalid marks")
	private double english;
	
	@PositiveOrZero
	@Max(value = 100, message = "Invalid marks")
	private double maths;
	
	@PositiveOrZero
	@Max(value = 100, message = "Invalid marks")
	private double science;
	
	@PositiveOrZero
	@Max(value = 100, message = "Invalid marks")
	private double ss;
}
