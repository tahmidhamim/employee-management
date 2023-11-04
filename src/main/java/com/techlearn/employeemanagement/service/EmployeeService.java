package com.techlearn.employeemanagement.service;

import com.techlearn.employeemanagement.exception.ServiceException;
import com.techlearn.employeemanagement.info.CreateEmployeeInfo;
import com.techlearn.employeemanagement.info.EmployeeInfo;
import com.techlearn.employeemanagement.info.UpdateEmployeeInfo;
import org.springframework.data.domain.Page;

public interface EmployeeService {
    EmployeeInfo saveEmployee(CreateEmployeeInfo info) throws ServiceException;
    EmployeeInfo getEmployeeById(String id) throws ServiceException;
    Page<EmployeeInfo> getEmployeeList(int page, int size) throws ServiceException;
    EmployeeInfo updateEmployee(String id, UpdateEmployeeInfo info) throws ServiceException;
    void deleteEmployeeById(String id) throws ServiceException;
}
