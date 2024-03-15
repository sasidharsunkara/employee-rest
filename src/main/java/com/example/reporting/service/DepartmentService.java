package com.example.reporting.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.reporting.entity.Department;
import com.example.reporting.model.DepartmentDetails;
import com.example.reporting.repository.DepartmentRepository;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentDetails createDepartment(DepartmentDetails departmentDetails){

        Department department = new Department();
        BeanUtils.copyProperties(departmentDetails, department);
        
        department = departmentRepository.save(department);
        BeanUtils.copyProperties(department, departmentDetails);
        
        return departmentDetails;
    }
    
}
