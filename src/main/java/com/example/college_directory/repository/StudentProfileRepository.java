package com.example.college_directory.repository;

import com.example.college_directory.model.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    List<StudentProfile> findByDepartment_Id(Long departmentId);

}
