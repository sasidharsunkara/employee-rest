package com.example.reporting.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.reporting.entity.Department;
import com.example.reporting.model.DepartmentDetails;
import com.example.reporting.model.EmployeeDetails;
import com.example.reporting.repository.DepartmentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.runner.RunWith;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testGetEmployee() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employee/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").exists())
            .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testCreateEmployee() throws Exception {
        EmployeeDetails employee = new EmployeeDetails();
        employee.setFirstName("Sally");
        employee.setLastName("Smith");
        employee.setEmail("sally@localhost.com");
        
        Department department = createDepartment();
        employee.setDepartmentId(department.getId());

        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post("/employee")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(mapper.writeValueAsString(employee))
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.lastName").exists())
            .andExpect(jsonPath("$.id").value(1));
    }

    private Department createDepartment() {
        Department department = new Department();
        department.setName("Sales");
        department.setLocation("New York");
        return departmentRepository.save(department);
    }

}