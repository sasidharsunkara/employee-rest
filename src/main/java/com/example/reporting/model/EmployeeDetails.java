package com.example.reporting.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean deleted;
    private DepartmentDetails departmentDetails;
    private Long departmentId;
}
