package com.techlearn.employeemanagement.dto;

public class ApiError {
    private String message;

    public ApiError(String message) {
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
