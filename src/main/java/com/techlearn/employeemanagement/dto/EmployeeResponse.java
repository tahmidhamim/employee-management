package com.techlearn.employeemanagement.dto;

import com.techlearn.employeemanagement.model.EmployeeModel;
import com.techlearn.employeemanagement.util.Converter;

public class EmployeeResponse {
    private String employeeId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String nid;
    private String bankAccount;
    private String bankName;
    private String designation;
    private int salary;
    private String managerId;
    private String dateOfBirth;
    private String joinedAt;

    public EmployeeResponse(EmployeeModel model) {
        setEmployeeId(model.getEmployeeId());
        setName(model.getName());
        setEmail(model.getEmail());
        setPhone(model.getPhone());
        setAddress(model.getAddress());
        setNid(model.getNid());
        setBankAccount(model.getBankAccount());
        setBankName(model.getBankName());
        setDesignation(String.valueOf(model.getDesignation()));
        setSalary(model.getSalary());
        setManagerId(model.getManagerId());
        setDateOfBirth(Converter.toString(model.getDateOfBirth()));
        setJoinedAt(Converter.toString(model.getJoinedAt()));
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
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
        this.managerId = managerId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(String joinedAt) {
        this.joinedAt = joinedAt;
    }
}
