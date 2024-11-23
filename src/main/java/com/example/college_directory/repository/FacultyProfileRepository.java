package com.example.college_directory.repository;

import com.example.college_directory.model.FacultyProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyProfileRepository extends JpaRepository<FacultyProfile, Long> {
    List<FacultyProfile> findByDepartmentId(Long departmentId);
}
