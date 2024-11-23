package com.example.college_directory.repository;

import com.example.college_directory.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Deque;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String departmentName);
}
