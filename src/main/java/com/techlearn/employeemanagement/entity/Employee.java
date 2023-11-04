package com.techlearn.employeemanagement.entity;

import com.techlearn.employeemanagement.constant.Designation;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @Column(name = "employee_id")
    private String employeeId;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "address")
    private String address;
    @Column(name = "nid")
    private String nid;
    @Column(name = "bank_account")
    private String bankAccount;
    @Column(name = "bank_name")
    private String bankName;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "designation")
    private Designation designation;
    @Column(name = "salary")
    private int salary;
    @Column(name = "manager_id")
    private String managerId;
    @Column(name = "date_of_birth")
    private Instant dateOfBirth;
    @Column(name = "joined_at")
    private Instant joinedAt;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "created_at")
    private Instant createdAt;
    @Column(name = "last_updated_at")
    private Instant lastUpdatedAt;
    @Version
    @Column(name = "version_number")
    private long versionNumber;

    public Employee() {
    }

    public Employee(
            String employeeId,
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
            Instant joinedAt,
            Boolean isDeleted,
            Instant createdAt,
            Instant lastUpdatedAt,
            long versionNumber
    ) {
        setEmployeeId(employeeId);
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
        setDeleted(isDeleted);
        setCreatedAt(createdAt);
        setLastUpdatedAt(lastUpdatedAt);
        setVersionNumber(versionNumber);
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

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Instant lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public long getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(long versionNumber) {
        this.versionNumber = versionNumber;
    }
}
