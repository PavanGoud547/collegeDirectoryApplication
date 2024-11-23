package com.example.college_directory.DTO;

import java.util.List;

public class FacultyDto {
    private Long id;
    private Long userId; // For User
    private Long departmentId; // For Department
    private List<Long> courseIds; // For Courses
    private String specialization;

    // Constructor
    public FacultyDto(Long id, Long userId, Long departmentId, List<Long> courseIds, String specialization) {
        this.id = id;
        this.userId = userId;
        this.departmentId = departmentId;
        this.courseIds = courseIds;
        this.specialization = specialization;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public List<Long> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<Long> courseIds) {
        this.courseIds = courseIds;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Object getName() {
        return null;
    }

    public Object getDepartment() {
        return null;
    }
}