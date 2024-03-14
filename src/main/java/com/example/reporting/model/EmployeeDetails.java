package com.example.reporting.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeDetails {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
}
