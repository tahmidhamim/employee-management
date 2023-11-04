package com.techlearn.employeemanagement.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ServiceException {
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
