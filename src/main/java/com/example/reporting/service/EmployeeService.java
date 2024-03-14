package com.example.reporting.service;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reporting.entity.Employee;
import com.example.reporting.model.EmployeeDetails;
import com.example.reporting.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private static final Logger log = LogManager.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;


    final HashMap<Integer, EmployeeDetails> employeeMap = new HashMap<>();

    public EmployeeDetails createEmployee(EmployeeDetails employeeDetails) {
        // int id = employeeMap.size() + 1;
        // employee.setId(id);
        // employeeMap.put(id, employee);
        Employee employee = new Employee();
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmailAddress(employeeDetails.getEmail());
        log.info("Employee : {}", employee);

        Employee savedEmployee = employeeRepository.save(employee);
        log.info("Saved Employee : {}", savedEmployee);
        
        EmployeeDetails updatedEmployee = new EmployeeDetails();
        BeanUtils.copyProperties(savedEmployee, updatedEmployee);
        updatedEmployee.setEmail(savedEmployee.getEmailAddress());
        return updatedEmployee;
    }

    public String deleteEmployee(Integer id) {
        log.info("Employee Id : {}", id );
        if (!employeeMap.containsKey(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeMap.remove(id);
        return "Employee deleted successfully";
    }

    public EmployeeDetails getEmployee(Integer id) {
        if (employeeMap.containsKey(id)) {
            return employeeMap.get(id);
        }
        throw new RuntimeException("Employee not found");
    }

    public EmployeeDetails updateEmployee(Integer id, EmployeeDetails employee) {
        if (employeeMap.containsKey(id)) {
            employee.setId(id);
            employeeMap.put(id, employee);
            return employeeMap.get(id);
        }
        throw new RuntimeException("Employee not found");

    }
}
