package com.luv2code.cruddemo;

import java.util.List;

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
            createMultipleStudents(studentDAO);
            // readStudent(studentDAO);
            // queryForStudents(studentDAO);
            // queryForStudentByLastName(studentDAO);
            // updateStudent(studentDAO);
            // deleteStudent(studentDAO);
            // deleteAllStudents(studentDAO);
        };
    }

    private void deleteAllStudents(StudentDAO studentDAO) {
        System.out.println("Deleting all students");
        int numRowsDeleted = studentDAO.deleteAll();
        System.out.println("Deleted row count: " + numRowsDeleted);
    }

    private void deleteStudent(StudentDAO studentDAO) {
        int studentId = 3;
        System.out.println("Deleting student id: " + studentId);
        studentDAO.delete(studentId);
    }

    private void updateStudent(StudentDAO studentDAO) {
        int studentId = 1;
        String newName = "Scooby";

        System.out.println("Getting student with id: " + studentId);
        Student student = studentDAO.findById(studentId);

        System.out.println("Updating student name to : " + newName);
        student.setFirstName(newName);

        studentDAO.update(student);
    }

    private void queryForStudentByLastName(StudentDAO studentDAO) {

        List<Student> students = studentDAO.findByLastName("Duck");

        for (Student student : students) {
            System.out.println(student);
        }

    }

    private void queryForStudents(StudentDAO studentDAO) {
        List<Student> students = studentDAO.findAll();

        for (Student student : students) {
            System.out.println(student);
        }
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
