package com.example.reporting.repository;

import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.reporting.entity.Employee;

@Repository
@Where(clause = "deleted = false")
public interface EmployeeRepository extends JpaRepository<Employee, Long>, JpaSpecificationExecutor<Employee>{
 
    @Query("SELECT e FROM Employee e WHERE e.emailAddress = ?1")
    Employee findEmployeeByEmailAddress(String emailAddress);

    @Transactional
    @Modifying
    @Query("DELETE FROM Employee e WHERE e.department.id = ?1")
    void deleteEmployeesByDepartmentId(Long departmentId);
    
}
