package com.techlearn.employeemanagement.dao;

import com.techlearn.employeemanagement.constant.Designation;
import com.techlearn.employeemanagement.entity.Employee;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
    long countByIsDeletedFalse();

    Optional<Employee> findByEmployeeIdAndIsDeletedFalse(@Param("employeeId") String employeeId);

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select e from Employee e where e.employeeId = :id and e.isDeleted = false")
    Optional<Employee> lockAndFindEmployeeById(@Param("id") String id);

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Employee> findByNid(@Param("nid") String nid);

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Employee> findByEmail(@Param("email") String email);

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Employee> findByPhone(@Param("phone") String phone);

    Page<Employee> findByIsDeletedFalseOrderByNameAsc(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Employee e set " +
            "e.name = :name, " +
            "e.email = :email, " +
            "e.phone = :phone, " +
            "e.address = :address, " +
            "e.nid = :nid, " +
            "e.bankAccount = :bankAccount, " +
            "e.bankName = :bankName, " +
            "e.designation = :designation, " +
            "e.salary = :salary, " +
            "e.managerId = :managerId, " +
            "e.dateOfBirth = :dateOfBirth, " +
            "e.joinedAt = :joinedAt, " +
            "e.isDeleted = false, " +
            "e.lastUpdatedAt = :lastUpdatedAt, " +
            "e.versionNumber = :versionNumber " +
            "where e.employeeId = :employeeId " +
            "and e.versionNumber = :versionNumber - 1L")
    void updateEmployee(
            @Param("employeeId") String employeeId,
            @Param("name") String name,
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("address") String address,
            @Param("nid") String nid,
            @Param("bankAccount") String bankAccount,
            @Param("bankName") String bankName,
            @Param("designation") Designation designation,
            @Param("salary") Integer salary,
            @Param("managerId") String managerId,
            @Param("dateOfBirth") Instant dateOfBirth,
            @Param("joinedAt") Instant joinedAt,
            @Param("lastUpdatedAt") Instant lastUpdatedAt,
            @Param("versionNumber") long versionNumber
    );

    @Transactional
    @Modifying
    @Query("update Employee e set " +
            "e.name = :name, " +
            "e.email = :email, " +
            "e.phone = :phone, " +
            "e.address = :address, " +
            "e.nid = :nid, " +
            "e.bankAccount = :bankAccount, " +
            "e.bankName = :bankName, " +
            "e.designation = :designation, " +
            "e.salary = :salary, " +
            "e.managerId = :managerId, " +
            "e.dateOfBirth = :dateOfBirth, " +
            "e.joinedAt = :joinedAt, " +
            "e.lastUpdatedAt = :lastUpdatedAt, " +
            "e.versionNumber = :versionNumber " +
            "where e.employeeId = :employeeId " +
            "and e.isDeleted = false " +
            "and e.versionNumber = :versionNumber - 1L")
    void updateEmployeeById(
            @Param("employeeId") String employeeId,
            @Param("name") String name,
            @Param("email") String email,
            @Param("phone") String phone,
            @Param("address") String address,
            @Param("nid") String nid,
            @Param("bankAccount") String bankAccount,
            @Param("bankName") String bankName,
            @Param("designation") Designation designation,
            @Param("salary") Integer salary,
            @Param("managerId") String managerId,
            @Param("dateOfBirth") Instant dateOfBirth,
            @Param("joinedAt") Instant joinedAt,
            @Param("lastUpdatedAt") Instant lastUpdatedAt,
            @Param("versionNumber") long versionNumber
    );

    @Transactional
    @Modifying
    @Query("update Employee e set " +
            "e.isDeleted = true, " +
            "e.lastUpdatedAt = :lastUpdatedAt, " +
            "e.versionNumber = :versionNumber " +
            "where e.employeeId = :employeeId " +
            "and e.isDeleted = false " +
            "and e.versionNumber = :versionNumber - 1L")
    void deleteEmployeeById(
            @Param("employeeId") String employeeId,
            @Param("lastUpdatedAt") Instant lastUpdatedAt,
            @Param("versionNumber") long versionNumber
    );
}
