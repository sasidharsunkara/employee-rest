package com.example.reporting.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.reporting.model.EmployeeDetails;
import com.example.reporting.service.EmployeeService;

import lombok.extern.log4j.Log4j2;

@Log4j2
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
    public EmployeeDetails getEmployee(@PathVariable("id") Integer id) {
        log.info("EmployeeController.getEmployee");
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
        return employeeService.deleteEmployee(Arrays.asList(id));
    }


    /**
     * This method is used to delete an Employee object by its ID.
     * 
     * @param id This is the ID of the employee to be deleted.
     * @return String This returns a success message after deletion.
     * @throws RuntimeException when the employee with the given ID is not found.
     */
    @DeleteMapping("/employee")
    public String deleteEmployee(@RequestParam("id") List<Integer> ids) {
        return employeeService.deleteEmployee(ids);
    }

    /**
     * This method is used to create a new Employee object.
     * 
     * @param employee This is the employee object to be created.
     * @return Employee This returns the newly created employee object.
     */
    @PostMapping("/employee")
    public EmployeeDetails createEmployee(@RequestBody EmployeeDetails employee) {
        log.info("EmployeeController.createEmployee : {}", employee);
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
    public EmployeeDetails updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDetails employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/employee/department/{id}")
    public String deleteEmployeesByDepartment(@PathVariable("id") Long departmentId) {
        return employeeService.deleteEmployeeByDepartment(departmentId);
    }

}