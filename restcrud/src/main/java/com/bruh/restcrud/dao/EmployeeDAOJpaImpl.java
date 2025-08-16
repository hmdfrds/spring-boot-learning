package com.bruh.restcrud.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruh.restcrud.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {

    private EntityManager em;

    @Autowired
    public EmployeeDAOJpaImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = em.createQuery("FROM Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {
        Employee employee = em.find(Employee.class, id);
        return employee;
    }

    @Override
    public void deleteById(int id) {
        Employee employee = em.find(Employee.class, id);
        em.remove(employee);
    }

    @Override
    public Employee save(Employee employee) {
        Employee dbEmployee = em.merge(employee);
        return dbEmployee;
    }

}
