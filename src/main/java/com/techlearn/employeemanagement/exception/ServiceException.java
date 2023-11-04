package com.techlearn.employeemanagement.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {
    private final HttpStatus status;
    private final String message;

    public ServiceException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
