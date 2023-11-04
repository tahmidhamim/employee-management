package com.techlearn.employeemanagement.controller;

import com.techlearn.employeemanagement.constant.Designation;
import com.techlearn.employeemanagement.dto.CreateEmployeeRequest;
import com.techlearn.employeemanagement.dto.EmployeeResponse;
import com.techlearn.employeemanagement.dto.UpdateEmployeeRequest;
import com.techlearn.employeemanagement.exception.ServiceException;
import com.techlearn.employeemanagement.model.CreateEmployeeModel;
import com.techlearn.employeemanagement.model.EmployeeModel;
import com.techlearn.employeemanagement.model.UpdateEmployeeModel;
import com.techlearn.employeemanagement.service.EmployeeService;
import com.techlearn.employeemanagement.util.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employee")
public class EmployeeControllerImpl implements EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Override
    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody CreateEmployeeRequest request) {
        try {
            CreateEmployeeModel createModel = new CreateEmployeeModel(
                    request.getName(),
                    request.getEmail(),
                    request.getPhone(),
                    request.getAddress(),
                    request.getNid(),
                    request.getBankAccount(),
                    request.getBankName(),
                    Designation.valueOf(request.getDesignation()),
                    request.getSalary(),
                    request.getManagerId(),
                    Converter.toInstant(request.getDateOfBirth()),
                    Converter.toInstant(request.getJoinedAt())
            );
            EmployeeModel savedInfo = employeeService.saveEmployee(createModel);
            EmployeeResponse response = new EmployeeResponse(savedInfo);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable String id) {
        try {
            EmployeeModel model = employeeService.getEmployeeById(id);
            EmployeeResponse response = new EmployeeResponse(model);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<?> getEmployeeList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Page<EmployeeModel> modelPage = employeeService.getEmployeeList(page, size);

            List<EmployeeResponse> employeeList = modelPage.getContent().stream()
                    .map(EmployeeResponse::new)
                    .collect(Collectors.toList());

            Page<EmployeeResponse> responsePage = new PageImpl<>(employeeList, modelPage.getPageable(), modelPage.getTotalElements());
            return new ResponseEntity<>(responsePage, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<?> updateEmployeeById(
            @PathVariable String id,
            @RequestBody UpdateEmployeeRequest request
    ) {
        try {
            UpdateEmployeeModel model = new UpdateEmployeeModel(
                    request.getName(),
                    request.getEmail(),
                    request.getPhone(),
                    request.getAddress(),
                    request.getNid(),
                    request.getBankAccount(),
                    request.getBankName(),
                    Designation.valueOf(request.getDesignation()),
                    request.getSalary(),
                    request.getManagerId(),
                    Converter.toInstant(request.getDateOfBirth()),
                    Converter.toInstant(request.getJoinedAt())
            );
            EmployeeModel updatedInfo = employeeService.updateEmployee(id, model);
            EmployeeResponse response = new EmployeeResponse(updatedInfo);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployeeById(@PathVariable String id) {
        try {
            employeeService.deleteEmployeeById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ServiceException e) {
            return new ResponseEntity<>(e.getMessage(), e.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
