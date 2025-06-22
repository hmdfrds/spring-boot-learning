package com.luv2code.cruddemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			// createStudent(studentDAO);
			// createMultipleStudents(studentDAO);
			readStudent(studentDAO);
		};
	}

	private void readStudent(StudentDAO studentDAO) {
		System.out.println("Creating a new student object ...");

		Student newStudent = new Student("Paul", "Doe", "paul@test.com");
		studentDAO.save(newStudent);

		int theId = newStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		System.out.println("Retrieving student with id: " + theId);
		Student student = studentDAO.findById(theId);

		System.out.println("Found the student: " + student);

	}

	private void createStudent(StudentDAO studentDAO) {
		System.out.println("Creating new student object ...");

		Student newStudent = new Student("Paul", "Doe", "paul@test.com");
		studentDAO.save(newStudent);

		System.out.println("Saved student. Generated id: " + newStudent.getId());
	}

	private void createMultipleStudents(StudentDAO studentDAO) {
		System.err.println("Creating 3 students object ...");

		Student newStudent1 = new Student("Paul", "Doe", "paul@test.com");
		Student newStudent2 = new Student("Mary", "Public", "mary@test.com");
		Student newStudent3 = new Student("Bonita", "Apple", "bonita@test.com");

		studentDAO.save(newStudent1);
		studentDAO.save(newStudent2);
		studentDAO.save(newStudent3);
	}

}
