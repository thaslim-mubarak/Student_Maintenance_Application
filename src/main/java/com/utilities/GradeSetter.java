package com.utilities;

import java.util.List;

import com.entity.Marks;
import com.entity.Users;

import jakarta.validation.ValidationException;

public class GradeSetter {

	public static Grades setGrade(Users user) {

		List<Marks> marks = user.getMarks();

		double total = 0;
		double grade_points = 0;

		if (marks.size() <= 3 && marks.size() >= 0) {

			for (Marks m : marks) {
				total += m.getLanguage() + m.getEnglish() + m.getMaths() + m.getScience() + m.getSs();
			}

			switch (marks.size()) {
			case 1: {
				grade_points = total / 50;
				break;
			}
			case 2: {
				grade_points = total / 100;
				break;
			}
			case 3: {
				grade_points = total / 150;
				break;
			}
			}

			if (grade_points >= 0 && grade_points <= 10) {
				if (grade_points == 0) {
					return null;
				} else if (grade_points >= 9.1 && grade_points <= 10.0) {
					return Grades.O;
				} else if (grade_points >= 8.1 && grade_points <= 9.0) {
					return Grades.A;
				} else if (grade_points >= 7.1 && grade_points <= 8.0) {
					return Grades.B;
				} else if (grade_points >= 6.1 && grade_points <= 7.0) {
					return Grades.C;
				} else if (grade_points >= 5.1 && grade_points <= 6.0) {
					return Grades.D;
				} else if (grade_points >= 4.1 && grade_points <= 5.0) {
					return Grades.E;
				} else {
					return Grades.FAIL;
				}
			} else {
				throw new ValidationException("Marks should be between 0 and 500");
			}
		} else {
			throw new ValidationException("Only 3 set of marks is allowed");
		}

	}
}
