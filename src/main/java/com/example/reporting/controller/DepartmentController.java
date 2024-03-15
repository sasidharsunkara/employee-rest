package com.example.reporting.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.reporting.model.DepartmentDetails;
import com.example.reporting.service.DepartmentService;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/department")
    public DepartmentDetails createDepartment(@RequestBody final DepartmentDetails departmentDetails){        
        return departmentService.createDepartment(departmentDetails);
    }
}