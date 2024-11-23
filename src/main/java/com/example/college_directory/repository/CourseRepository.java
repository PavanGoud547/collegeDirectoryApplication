package com.example.college_directory.repository;


import com.example.college_directory.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findByDepartmentId(Long departmentId);
    List<Course> findByFacultyId(Long facultyId);
}