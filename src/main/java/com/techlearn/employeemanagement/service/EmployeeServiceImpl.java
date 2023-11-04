package com.techlearn.employeemanagement.service;

import com.techlearn.employeemanagement.dao.EmployeeRepository;
import com.techlearn.employeemanagement.entity.Employee;
import com.techlearn.employeemanagement.exception.BadRequestException;
import com.techlearn.employeemanagement.exception.ConflictException;
import com.techlearn.employeemanagement.exception.NotFoundException;
import com.techlearn.employeemanagement.exception.ServiceException;
import com.techlearn.employeemanagement.info.CreateEmployeeInfo;
import com.techlearn.employeemanagement.info.EmployeeInfo;
import com.techlearn.employeemanagement.info.UpdateEmployeeInfo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    private Employee checkExistence(EmployeeInfo info) throws ConflictException {
        Optional<Employee> employee;
        if (info.getNid() != null) {
            employee = employeeRepository.lockAndFindEmployeeByNid(info.getNid());
            if (employee.isPresent()) {
                if (employee.get().getDeleted()) {
                    return employee.get();
                } else {
                    throw new ConflictException("Nid already exists!");
                }
            }
        }
        employee = employeeRepository.lockAndFindEmployeeByEmail(info.getEmail());
        if (employee.isPresent()) {
            if (employee.get().getDeleted()) {
                return employee.get();
            } else {
                throw new ConflictException("Email already exists!");
            }
        }
        if (info.getPhone() != null) {
            employee = employeeRepository.lockAndFindEmployeeByPhone(info.getPhone());
            if (employee.isPresent()) {
                if (employee.get().getDeleted()) {
                    return employee.get();
                } else {
                    throw new ConflictException("Phone number already exists!");
                }
            }
        }
        return null;
    }

    @Override
    @Transactional
    public EmployeeInfo saveEmployee(CreateEmployeeInfo info) throws ServiceException {
        try {
            EmployeeInfo employee = new EmployeeInfo(
                    info.getName(),
                    info.getEmail(),
                    info.getPhone(),
                    info.getAddress(),
                    info.getNid(),
                    info.getBankAccount(),
                    info.getBankName(),
                    info.getDesignation(),
                    info.getSalary(),
                    info.getManagerId(),
                    info.getDateOfBirth(),
                    info.getJoinedAt()
            );
            Employee existingEmployee = checkExistence(employee);
            employee.setEmployeeInfo(existingEmployee);
            if (existingEmployee != null) {
                employeeRepository.updateEmployee(
                        employee.getEmployeeId(),
                        employee.getName(),
                        employee.getEmail(),
                        employee.getPhone(),
                        employee.getAddress(),
                        employee.getNid(),
                        employee.getBankAccount(),
                        employee.getBankName(),
                        employee.getDesignation(),
                        employee.getSalary(),
                        employee.getManagerId(),
                        employee.getDateOfBirth(),
                        employee.getJoinedAt(),
                        employee.getLastUpdatedAt(),
                        employee.getVersionNumber()
                );
                return employee;
            } else {
                Employee entity = employeeRepository.save(employee.toEntity());
                return new EmployeeInfo(entity);
            }
        } catch (BadRequestException | ConflictException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public EmployeeInfo getEmployeeById(String id) throws ServiceException {
        try {
            Employee entity = employeeRepository.findEmployeeById(id)
                    .orElseThrow(() -> new NotFoundException("No employee found with ID: " + id));
            return new EmployeeInfo(entity);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Page<EmployeeInfo> getEmployeeList(int page, int size) throws ServiceException {
        try {
            if (size > 100) {
                throw new BadRequestException("Page size cannot be greater than 100");
            }

            Pageable pageable = PageRequest.of(page, size);
            Page<Employee> entityPage = employeeRepository.findEmployeeList(pageable);

            List<EmployeeInfo> employeeList = entityPage.getContent().stream()
                    .map(entity -> {
                        try {
                            return new EmployeeInfo(entity);
                        } catch (BadRequestException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());

            return new PageImpl<>(employeeList, entityPage.getPageable(), entityPage.getTotalElements());
        } catch (BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    @Transactional
    public EmployeeInfo updateEmployee(String id, UpdateEmployeeInfo info) throws ServiceException {
        try {
            Employee existingEmployee = employeeRepository.lockAndFindEmployeeById(id)
                    .orElseThrow(() -> new NotFoundException("No employee found with ID: " + id));

            EmployeeInfo employee = new EmployeeInfo(existingEmployee);
            employee.setName(info.getName() != null ? info.getName() : employee.getName());
            employee.setEmail(info.getEmail() != null ? info.getEmail() : employee.getEmail());
            employee.setPhone(info.getPhone() != null ? info.getPhone() : employee.getPhone());
            employee.setAddress(info.getAddress() != null ? info.getAddress() : employee.getAddress());
            employee.setNid(info.getNid() != null ? info.getNid() : employee.getNid());
            employee.setBankAccount(info.getBankAccount() != null ? info.getBankAccount() : employee.getBankAccount());
            employee.setBankName(info.getBankName() != null ? info.getBankName() : employee.getBankName());
            employee.setDesignation(info.getDesignation() != null ? info.getDesignation() : employee.getDesignation());
            employee.setSalary(info.getSalary() != null ? info.getSalary() : employee.getSalary());
            employee.setManagerId(info.getManagerId() != null ? info.getManagerId() : employee.getManagerId());
            employee.setDateOfBirth(info.getDateOfBirth() != null ? info.getDateOfBirth() : employee.getDateOfBirth());
            employee.setJoinedAt(info.getJoinedAt() != null ? info.getJoinedAt() : employee.getJoinedAt());
            employee.setLastUpdatedAt(Instant.now());
            employee.setVersionNumber(existingEmployee.getVersionNumber() + 1);

            employeeRepository.updateEmployeeById(
                    id,
                    employee.getName(),
                    employee.getEmail(),
                    employee.getPhone(),
                    employee.getAddress(),
                    employee.getNid(),
                    employee.getBankAccount(),
                    employee.getBankName(),
                    employee.getDesignation(),
                    employee.getSalary(),
                    employee.getManagerId(),
                    employee.getDateOfBirth(),
                    employee.getJoinedAt(),
                    employee.getLastUpdatedAt(),
                    employee.getVersionNumber()
            );

            return employee;
        } catch (NotFoundException | BadRequestException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteEmployeeById(String id) throws ServiceException {
        try {
            Employee employee = employeeRepository.lockAndFindEmployeeById(id)
                    .orElseThrow(() -> new NotFoundException("No employee found with ID: " + id));
            employeeRepository.deleteEmployeeById(id, Instant.now(), employee.getVersionNumber() + 1L);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
