package com.techlearn.employeemanagement.info;

import com.techlearn.employeemanagement.constant.Designation;
import com.techlearn.employeemanagement.entity.Employee;
import com.techlearn.employeemanagement.exception.BadRequestException;
import com.techlearn.employeemanagement.util.Converter;
import com.techlearn.employeemanagement.util.Validator;

import java.time.Instant;
import java.util.UUID;

public class EmployeeInfo {
    private String employeeId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String nid;
    private String bankAccount;
    private String bankName;
    private Designation designation;
    private int salary;
    private String managerId;
    private Instant dateOfBirth;
    private Instant joinedAt;
    private Boolean isDeleted;
    private Instant createdAt;
    private Instant lastUpdatedAt;
    private long versionNumber;

    public EmployeeInfo(
            String name,
            String email,
            String phone,
            String address,
            String nid,
            String bankAccount,
            String bankName,
            Designation designation,
            int salary,
            String managerId,
            Instant dateOfBirth,
            Instant joinedAt
    ) throws BadRequestException {
        setEmployeeId(UUID.randomUUID().toString());
        setName(name);
        setEmail(email);
        setPhone(phone);
        setAddress(address);
        setNid(nid);
        setBankAccount(bankAccount);
        setBankName(bankName);
        setDesignation(designation);
        setSalary(salary);
        setManagerId(managerId);
        setDateOfBirth(dateOfBirth);
        setJoinedAt(joinedAt);
        setDeleted(false);
        setCreatedAt(Instant.now());
        setLastUpdatedAt(Instant.now());
        setVersionNumber(1);
    }

    public EmployeeInfo(Employee employee) throws BadRequestException {
        setEmployeeId(employee.getEmployeeId());
        setName(employee.getName());
        setEmail(employee.getEmail());
        setPhone(employee.getPhone());
        setAddress(employee.getAddress());
        setNid(employee.getNid());
        setBankAccount(employee.getBankAccount());
        setBankName(employee.getBankName());
        setDesignation(employee.getDesignation());
        setSalary(employee.getSalary());
        setManagerId(employee.getManagerId());
        setDateOfBirth(employee.getDateOfBirth());
        setJoinedAt(employee.getJoinedAt());
        setDeleted(employee.getDeleted());
        setCreatedAt(employee.getCreatedAt());
        setLastUpdatedAt(employee.getLastUpdatedAt());
        setVersionNumber(employee.getVersionNumber());
    }

    public String getEmployeeId() {
        return employeeId;
    }

    private void setEmployeeId(String employeeId) throws BadRequestException {
        if (employeeId == null || employeeId.isBlank()) {
            throw new BadRequestException("Employee ID cannot be null or blank");
        }
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws BadRequestException {
        if (name == null || name.isBlank()) {
            throw new BadRequestException("Name cannot be null or blank");
        }
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws BadRequestException {
        if (email == null || email.isBlank()) {
            throw new BadRequestException("Email cannot be null or blank");
        }
        if (!Validator.isValidEmail(email)) {
            throw new BadRequestException("Email is not valid");
        }
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone != null && phone.isBlank()) {
            phone = null;
        }
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address != null && address.isBlank()) {
            address = null;
        }
        this.address = address;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        if (nid != null && nid.isBlank()) {
            nid = null;
        }
        this.nid = nid;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        if (bankAccount != null && bankAccount.isBlank()) {
            bankAccount = null;
        }
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        if (bankName != null && bankName.isBlank()) {
            bankName = null;
        }
        this.bankName = bankName;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) throws BadRequestException {
        if (designation == null) {
            throw new BadRequestException("Designation cannot be null");
        }
        this.designation = designation;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        if (managerId != null && managerId.isBlank()) {
            managerId = null;
        }
        this.managerId = managerId;
    }

    public Instant getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Instant dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Instant getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(Instant joinedAt) throws BadRequestException {
        if (joinedAt == null) {
            throw new BadRequestException("Joining Time cannot be null");
        }
        this.joinedAt = joinedAt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    private void setCreatedAt(Instant createdAt) throws BadRequestException {
        if (createdAt == null) {
            throw new BadRequestException("Create Time cannot be null");
        }
        this.createdAt = createdAt;
    }

    public Instant getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Instant lastUpdatedAt) throws BadRequestException {
        if (lastUpdatedAt == null) {
            throw new BadRequestException("Last Update Time cannot be null");
        }
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public long getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(long versionNumber) throws BadRequestException {
        if (versionNumber < 1) {
            throw new BadRequestException("Version Number should be greater than zero");
        }
        this.versionNumber = versionNumber;
    }

    public void setEmployeeInfo(Employee employee) throws BadRequestException {
        if (employee != null) {
            setEmployeeId(employee.getEmployeeId());
            setPhone(getPhone() == null ? employee.getPhone() : getPhone());
            setAddress(getAddress() == null ? employee.getAddress() : getAddress());
            setNid(getNid() == null ? employee.getNid() : getNid());
            setBankAccount(getBankAccount() == null ? employee.getBankAccount() : getBankAccount());
            setBankName(getBankName() == null ? employee.getBankName() : getBankName());
            setManagerId(getManagerId() == null ? employee.getManagerId() : getManagerId());
            setDateOfBirth(getDateOfBirth() == null ? employee.getDateOfBirth() : getDateOfBirth());
            setCreatedAt(employee.getCreatedAt());
            setVersionNumber(employee.getVersionNumber() + 1);
        }
    }

    public Employee toEntity() {
        return new Employee(
                employeeId,
                name,
                email,
                phone,
                address,
                nid,
                bankAccount,
                bankName,
                designation,
                salary,
                managerId,
                dateOfBirth,
                joinedAt,
                isDeleted,
                createdAt,
                lastUpdatedAt,
                versionNumber
        );
    }
}
