package com.example.reporting.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationError {
    private String message;
    private Long errorCode;

    public ApplicationError(String message, Long errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
