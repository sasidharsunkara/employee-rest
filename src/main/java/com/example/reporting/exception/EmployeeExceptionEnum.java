package com.example.reporting.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum EmployeeExceptionEnum {

    INVALID_EMPLOYEE_ID(10002L, HttpStatus.BAD_REQUEST, "Invalid employee Id"),
    INVALID_DEPT_ID(10003L, HttpStatus.BAD_REQUEST, "Invalid dept Id");

    private Long errorCode;
    private HttpStatus httpStatus;
    private String errorMessage;

    EmployeeExceptionEnum(Long errorCode, HttpStatus httpStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }
}
