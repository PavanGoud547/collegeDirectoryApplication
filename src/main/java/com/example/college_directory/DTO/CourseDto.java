package com.example.college_directory.DTO;

public class CourseDto {

    private Long id;
    private String name;
    private String description;
    private String departmentName;

    public CourseDto() {
    }

    public CourseDto(Long id, String name, String description, String departmentName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.departmentName = departmentName;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}