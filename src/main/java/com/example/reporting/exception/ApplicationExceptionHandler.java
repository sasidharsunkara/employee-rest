package com.example.reporting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(EmployeeAppException.class)
    public ResponseEntity<?> handleEmployeeAppException(EmployeeAppException e) {
        EmployeeExceptionEnum exceptionEnum = e.getExceptionEnum();
        return new ResponseEntity<>(new ApplicationError(exceptionEnum.getErrorMessage(),  exceptionEnum.getErrorCode()), exceptionEnum.getHttpStatus());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e) {
        // This will catch any other exceptions not handled by specific handlers
        return new ResponseEntity<>(new ApplicationError("Employee application error",  99999L), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
