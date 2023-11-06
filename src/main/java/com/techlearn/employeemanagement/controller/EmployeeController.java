package com.techlearn.employeemanagement.controller;

import com.techlearn.employeemanagement.dto.CreateEmployeeRequest;
import com.techlearn.employeemanagement.dto.UpdateEmployeeRequest;
import org.springframework.http.ResponseEntity;

public interface EmployeeController {
    ResponseEntity<?> createEmployee(CreateEmployeeRequest request);
    ResponseEntity<?> getEmployeeById(String id);
    ResponseEntity<?> getEmployeeList(int page, int size);
    ResponseEntity<?> updateEmployeeById(String id, UpdateEmployeeRequest request);
    ResponseEntity<?> deleteEmployeeById(String id);
    ResponseEntity<?> countEmployees();
}
