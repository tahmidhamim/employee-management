package com.techlearn.employeemanagement.service;

import com.techlearn.employeemanagement.exception.ServiceException;
import com.techlearn.employeemanagement.model.CreateEmployeeModel;
import com.techlearn.employeemanagement.model.EmployeeModel;
import com.techlearn.employeemanagement.model.UpdateEmployeeModel;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    EmployeeModel saveEmployee(CreateEmployeeModel info) throws ServiceException;
    EmployeeModel getEmployeeById(String id) throws ServiceException;
    Page<EmployeeModel> getEmployeeList(int page, int size) throws ServiceException;
    EmployeeModel updateEmployee(String id, UpdateEmployeeModel info) throws ServiceException;
    void deleteEmployeeById(String id) throws ServiceException;
}
