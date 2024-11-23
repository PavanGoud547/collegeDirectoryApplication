package com.example.college_directory.DTO;

public class StudentDto {

    private Long id;
    private String name;
    private String email;
    private String departmentId;
    private String enrollmentNumber;

    public StudentDto(Long id, String name, String email, Long departmentId) {
    }

    public StudentDto(Long id, String name, String email, String department, String enrollmentNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.departmentId = departmentId;
        this.enrollmentNumber = enrollmentNumber;
    }

    public StudentDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String department) {
        this.departmentId = departmentId;
    }

    public String getEnrollmentNumber() {
        return enrollmentNumber;
    }

    public void setEnrollmentNumber(String enrollmentNumber) {
        this.enrollmentNumber = enrollmentNumber;
    }
}