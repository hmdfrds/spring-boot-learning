package com.bruh.restcrud.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bruh.restcrud.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
