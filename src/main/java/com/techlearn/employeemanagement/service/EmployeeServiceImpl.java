package com.techlearn.employeemanagement.service;

import com.techlearn.employeemanagement.dao.EmployeeRepository;
import com.techlearn.employeemanagement.entity.Employee;
import com.techlearn.employeemanagement.exception.BadRequestException;
import com.techlearn.employeemanagement.exception.ConflictException;
import com.techlearn.employeemanagement.exception.NotFoundException;
import com.techlearn.employeemanagement.exception.ServiceException;
import com.techlearn.employeemanagement.model.CreateEmployeeModel;
import com.techlearn.employeemanagement.model.EmployeeModel;
import com.techlearn.employeemanagement.model.UpdateEmployeeModel;
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

    private Employee checkExistence(EmployeeModel model) throws ConflictException {
        Optional<Employee> employee;
        if (model.getNid() != null) {
            employee = employeeRepository.findByNid(model.getNid());
            if (employee.isPresent()) {
                if (employee.get().getDeleted()) {
                    return employee.get();
                } else {
                    throw new ConflictException("Nid already exists!");
                }
            }
        }
        employee = employeeRepository.findByEmail(model.getEmail());
        if (employee.isPresent()) {
            if (employee.get().getDeleted()) {
                return employee.get();
            } else {
                throw new ConflictException("Email already exists!");
            }
        }
        if (model.getPhone() != null) {
            employee = employeeRepository.findByPhone(model.getPhone());
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
    public EmployeeModel saveEmployee(CreateEmployeeModel model) throws ServiceException {
        try {
            EmployeeModel employee = new EmployeeModel(
                    model.getName(),
                    model.getEmail(),
                    model.getPhone(),
                    model.getAddress(),
                    model.getNid(),
                    model.getBankAccount(),
                    model.getBankName(),
                    model.getDesignation(),
                    model.getSalary(),
                    model.getManagerId(),
                    model.getDateOfBirth(),
                    model.getJoinedAt()
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
                return new EmployeeModel(entity);
            }
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public EmployeeModel getEmployeeById(String id) throws ServiceException {
        try {
            Employee entity = employeeRepository.findEmployeeById(id)
                    .orElseThrow(() -> new NotFoundException("No employee found with ID: " + id));
            return new EmployeeModel(entity);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public Page<EmployeeModel> getEmployeeList(int page, int size) throws ServiceException {
        try {
            if (page < 0 || size < 0) {
                throw new BadRequestException("Page number or size cannot be less than 0");
            }
            if (size > 100) {
                throw new BadRequestException("Page size cannot be greater than 100");
            }

            Pageable pageable = PageRequest.of(page, size);
            Page<Employee> entityPage = employeeRepository.findEmployeeList(pageable);

            List<EmployeeModel> employeeList = entityPage.getContent().stream()
                    .map(entity -> {
                        try {
                            return new EmployeeModel(entity);
                        } catch (BadRequestException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .collect(Collectors.toList());

            return new PageImpl<>(employeeList, entityPage.getPageable(), entityPage.getTotalElements());
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    @Transactional
    public EmployeeModel updateEmployee(String id, UpdateEmployeeModel model) throws ServiceException {
        try {
            Employee existingEmployee = employeeRepository.lockAndFindEmployeeById(id)
                    .orElseThrow(() -> new NotFoundException("No employee found with ID: " + id));

            EmployeeModel employee = new EmployeeModel(existingEmployee);
            employee.setName(model.getName() != null ? model.getName() : employee.getName());
            employee.setEmail(model.getEmail() != null ? model.getEmail() : employee.getEmail());
            employee.setPhone(model.getPhone() != null ? model.getPhone() : employee.getPhone());
            employee.setAddress(model.getAddress() != null ? model.getAddress() : employee.getAddress());
            employee.setNid(model.getNid() != null ? model.getNid() : employee.getNid());
            employee.setBankAccount(model.getBankAccount() != null ? model.getBankAccount() : employee.getBankAccount());
            employee.setBankName(model.getBankName() != null ? model.getBankName() : employee.getBankName());
            employee.setDesignation(model.getDesignation() != null ? model.getDesignation() : employee.getDesignation());
            employee.setSalary(model.getSalary() != null ? model.getSalary() : employee.getSalary());
            employee.setManagerId(model.getManagerId() != null ? model.getManagerId() : employee.getManagerId());
            employee.setDateOfBirth(model.getDateOfBirth() != null ? model.getDateOfBirth() : employee.getDateOfBirth());
            employee.setJoinedAt(model.getJoinedAt() != null ? model.getJoinedAt() : employee.getJoinedAt());
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
        } catch (ServiceException e) {
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
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
