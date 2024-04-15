package com.example.reporting.exception;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
public class EmployeeAppException extends RuntimeException{
    
    private EmployeeExceptionEnum exceptionEnum;
    private String message;
    private Exception e;

    public EmployeeAppException(EmployeeExceptionEnum exceptionEnum, String message, Exception e) {
        super(message);
        this.exceptionEnum = exceptionEnum;
        this.message = message;
        this.e = e;
        log.error("EmployeeAppException : {}", message, e);
    }


    public EmployeeAppException(EmployeeExceptionEnum exceptionEnum) {
        this.exceptionEnum = exceptionEnum;
    }
}
