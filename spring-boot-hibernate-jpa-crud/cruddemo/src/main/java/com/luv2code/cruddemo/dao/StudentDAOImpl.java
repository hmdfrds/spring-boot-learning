package com.luv2code.cruddemo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class StudentDAOImpl implements StudentDAO {

    private EntityManager em;

    @Autowired
    public StudentDAOImpl(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void save(Student student) {
        em.persist(student);
    }

    public Student findById(Integer id) {
        return em.find(Student.class, id);
    }

    public List<Student> findAll() {
        TypedQuery<Student> query = em.createQuery("From Student", Student.class);
        return query.getResultList();
    }

    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> query = em.createQuery("From Student where lastName=:lastName", Student.class);
        query.setParameter("lastName", lastName);

        return query.getResultList();

    }

    @Transactional
    public void update(Student student) {
        em.merge(student);
    }

    @Transactional
    public void delete(int id) {
        Student student = em.find(Student.class, id);
        em.remove(student);
    }

    @Transactional
    public int deleteAll() {
        int numRowsDeleted = em.createQuery("DELETE FROM Student").executeUpdate();
        return numRowsDeleted;
    }

}
