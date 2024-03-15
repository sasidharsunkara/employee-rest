package com.example.reporting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reporting.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    
}
