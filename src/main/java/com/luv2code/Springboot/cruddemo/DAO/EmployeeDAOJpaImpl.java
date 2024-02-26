package com.luv2code.Springboot.cruddemo.DAO;

import com.luv2code.Springboot.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{

    //Define fields for EntityManager

    private EntityManager entityManager;
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager theEntityManager)
    {
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {

        //Create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);
        //Excute the query to find all the employees and get result list
        List<Employee> employees = theQuery.getResultList();
        //return results
        return employees;
    }

    @Override
    public Employee findById(int theId) {

        //Get the employee By Id
        Employee theEmployee = entityManager.find(Employee.class, theId);
        //return the employee
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        //Save Employee to DB
        Employee dbEmployee = entityManager.merge(theEmployee);
        //return the updated dbEmployee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        //Find the Employee by id
        Employee theEmployee = entityManager.find(Employee.class, theId);

        //Delete the employee
        entityManager.remove(theEmployee);

    }
}
