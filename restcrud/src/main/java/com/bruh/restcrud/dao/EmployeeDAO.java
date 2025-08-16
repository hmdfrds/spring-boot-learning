package com.bruh.restcrud.dao;

import java.util.List;

import com.bruh.restcrud.entity.Employee;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee findById(int id);

    Employee save(Employee employee);

    void deleteById(int id);
}
