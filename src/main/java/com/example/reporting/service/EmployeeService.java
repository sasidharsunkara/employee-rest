package com.example.reporting.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.example.reporting.mode.Employee;

@Service
public class EmployeeService {

    final HashMap<Integer, Employee> employeeMap = new HashMap<>();

    public Employee createEmployee(Employee employee) {
        int id = employeeMap.size() + 1;
        employee.setId(id);
        employeeMap.put(id, employee);
        return employee;
    }

    public String deleteEmployee(Integer id) {
        if (!employeeMap.containsKey(id)) {
            throw new RuntimeException("Employee not found");
        }
        employeeMap.remove(id);
        return "Employee deleted successfully";
    }

    public Employee getEmployee(Integer id) {
        if (employeeMap.containsKey(id)) {
            return employeeMap.get(id);
        }
        throw new RuntimeException("Employee not found");
    }

    public Employee updateEmployee(Integer id, Employee employee) {
        if (employeeMap.containsKey(id)) {
            employee.setId(id);
            employeeMap.put(id, employee);
            return employeeMap.get(id);
        }
        throw new RuntimeException("Employee not found");

    }
}
