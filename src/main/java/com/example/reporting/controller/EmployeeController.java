package com.example.reporting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.reporting.mode.Employee;
import com.example.reporting.service.EmployeeService;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * This method is used to get an Employee object by its ID.
     * 
     * @param id This is the ID of the employee to be returned.
     * @return Employee This returns the employee object with the given ID.
     * @throws RuntimeException when the employee with the given ID is not found.
     */
    @GetMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable("id") Integer id) {
        return employeeService.getEmployee(id);
    }

    /**
     * This method is used to delete an Employee object by its ID.
     * 
     * @param id This is the ID of the employee to be deleted.
     * @return String This returns a success message after deletion.
     * @throws RuntimeException when the employee with the given ID is not found.
     */
    @DeleteMapping("/employee/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        return employeeService.deleteEmployee(id);
    }

    /**
     * This method is used to create a new Employee object.
     * 
     * @param employee This is the employee object to be created.
     * @return Employee This returns the newly created employee object.
     */
    @PostMapping("/employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    /**
     * This method is used to update an existing Employee object.
     * 
     * @param id       This is the ID of the employee to be updated.
     * @param employee This is the new employee object to replace the existing one.
     * @return Employee This returns the updated employee object.
     * @throws RuntimeException when the employee with the given ID is not found.
     */
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

}