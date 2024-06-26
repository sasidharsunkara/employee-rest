package com.example.reporting.service;

import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reporting.entity.Department;
import com.example.reporting.entity.Employee;
import com.example.reporting.exception.EmployeeAppException;
import com.example.reporting.exception.EmployeeExceptionEnum;
import com.example.reporting.model.DepartmentDetails;
import com.example.reporting.model.EmployeeDetails;
import com.example.reporting.repository.DepartmentRepository;
import com.example.reporting.repository.EmployeeRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class EmployeeService {

    private static final Logger log = LogManager.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    final HashMap<Long, EmployeeDetails> employeeMap = new HashMap<>();

    public EmployeeDetails createEmployee(EmployeeDetails employeeDetails) {

        Long departmentId = employeeDetails.getDepartmentId();
        Department department = departmentId == null? null : departmentRepository.findById(departmentId).orElse(null);
        if (department == null) {
            throw new RuntimeException("Invalid department Id");
        }

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDetails, employee);
        employee.setEmailAddress(employeeDetails.getEmail());

        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);

        log.info("Saved Employee : {}", savedEmployee);

        EmployeeDetails updatedEmployee = new EmployeeDetails();
        BeanUtils.copyProperties(savedEmployee, updatedEmployee);
        updatedEmployee.setEmail(savedEmployee.getEmailAddress());

        DepartmentDetails departmentDetails = new DepartmentDetails();
        BeanUtils.copyProperties(department, departmentDetails);

        updatedEmployee.setDepartmentDetails(departmentDetails);
        return updatedEmployee;
    }

    public String deleteEmployee(List<Long> ids) {
        log.info("Employee Id : {}", ids);
        employeeRepository.deleteAllById(ids);
        return "Employee deleted successfully";
    }

    public EmployeeDetails getEmployee(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new EmployeeAppException(EmployeeExceptionEnum.INVALID_EMPLOYEE_ID);
        }
        EmployeeDetails employeeDetails = new EmployeeDetails();
        BeanUtils.copyProperties(employee, employeeDetails);
        return employeeDetails;
    }

    public EmployeeDetails updateEmployee(Long id, EmployeeDetails employee) {
        if (employeeMap.containsKey(id)) {
            employee.setId(id);
            employeeMap.put(id, employee);
            return employeeMap.get(id);
        }
        throw new RuntimeException("Employee not found");
    }

    public String deleteEmployeeByDepartment(Long departmentId) {
        log.info("Deleting the employees by departmentId : {}", departmentId);
        employeeRepository.deleteEmployeesByDepartmentId(departmentId);
        return "Employees deleted successfully";
    }
}
