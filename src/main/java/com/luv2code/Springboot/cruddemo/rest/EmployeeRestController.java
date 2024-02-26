package com.luv2code.Springboot.cruddemo.rest;

import com.luv2code.Springboot.cruddemo.entity.Employee;
import com.luv2code.Springboot.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService theEmployeeService)
    {
            employeeService = theEmployeeService;
    }
    @GetMapping("/employees")
    public List<Employee> findAll()
    {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable int employeeId)
    {
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee==null)
        {
            throw new RuntimeException("Employeed not found with id: "+ employeeId);
        }
        return theEmployee;
    }
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee)
    {
        //ALSO IF THEY SEND ID IN JSON.. SET ID TO '0'
        //THIS IS TO FORCE A SAVE ON ADD ITEM.. INSTEAD OF UPDATE
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee)
    {
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId)
    {
        Employee tempEmployee = employeeService.findById(employeeId);
        if(tempEmployee==null)
        {
            throw new RuntimeException("Employee not found with id: "+ employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Successfully deleted the employee with id: "+employeeId;
    }


    
}
