package com.techlearn.employeemanagement.model;

import com.techlearn.employeemanagement.constant.Designation;

import java.time.Instant;

public class UpdateEmployeeModel {
    private String name;
    private String email;
    private String phone;
    private String address;
    private String nid;
    private String bankAccount;
    private String bankName;
    private Designation designation;
    private Integer salary;
    private String managerId;
    private Instant dateOfBirth;
    private Instant joinedAt;

    public UpdateEmployeeModel(
            String name,
            String email,
            String phone,
            String address,
            String nid,
            String bankAccount,
            String bankName,
            Designation designation,
            Integer salary,
            String managerId,
            Instant dateOfBirth,
            Instant joinedAt
    ) {
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
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
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

    public void setJoinedAt(Instant joinedAt) {
        this.joinedAt = joinedAt;
    }
}
