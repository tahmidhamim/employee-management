package com.techlearn.employeemanagement.dto;

public class ErrorResponse {
    private String message;

    public ErrorResponse(String message) {
        setMessage(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
