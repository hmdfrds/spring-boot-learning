package com.bruh.restcrud.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruh.restcrud.entity.Employee;
import com.bruh.restcrud.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/employees")
public class EmployeeRestController {

    private EmployeeService employeeService;
    private ObjectMapper objectMapper;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }

        return employee;
    }

    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        employee.setId(0);
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee) {
        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    @PatchMapping("/{id}")
    public Employee patchEmployee(@PathVariable int id, @RequestBody Map<String, Object> payload) {
        Employee dbEmployee = employeeService.findById(id);

        if (dbEmployee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }

        if (payload.containsKey("id")) {
            throw new RuntimeException("Employee id not allowed in request body - " + id);
        }

        Employee patchedEmployee = apply(payload, dbEmployee);
        Employee employee = employeeService.save(patchedEmployee);

        return employee;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = employeeService.findById(id);

        if (employee == null) {
            throw new RuntimeException("Employee id not found - " + id);
        }
        employeeService.deleteById(id);
        return "Deleted employee id - " + id;
    }

    private Employee apply(Map<String, Object> payload, Employee dbEmployee) {
        ObjectNode employeeNode = objectMapper.convertValue(dbEmployee, ObjectNode.class);
        ObjectNode patchNode = objectMapper.convertValue(payload, ObjectNode.class);

        employeeNode.setAll(patchNode);

        return objectMapper.convertValue(employeeNode, Employee.class);
    }

}
