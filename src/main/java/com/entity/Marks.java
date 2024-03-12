package com.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	@JsonIgnore(value = true)
	@Schema(hidden = true)
	private int marks_id;
	
	@PositiveOrZero
	@Max(value = 100, message = " Invalid marks for language ")
	private double language;
	
	@PositiveOrZero
	@Max(value = 100, message = " Invalid marks for english ")
	private double english;
	
	@PositiveOrZero
	@Max(value = 100, message = " Invalid marks for maths ")
	private double maths;
	
	@PositiveOrZero
	@Max(value = 100, message = " Invalid marks for science ")
	private double science;
	
	@PositiveOrZero
	@Max(value = 100, message = " Invalid marks for ss ")
	private double ss;
}
