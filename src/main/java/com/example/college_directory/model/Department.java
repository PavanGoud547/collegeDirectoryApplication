package com.example.college_directory.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "department_name", nullable = false)
    private String name;

    private String Description;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @OneToMany(mappedBy = "department")
    private List<StudentProfile> students;

    @OneToMany(mappedBy = "department")
    private List<FacultyProfile> facultyMembers;

    @OneToMany(mappedBy = "department")
    private List<Course> courses;

    // Getters and Setters
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

    public List<StudentProfile> getStudents() {
        return students;
    }

    public void setStudents(List<StudentProfile> students) {
        this.students = students;
    }

    public List<FacultyProfile> getFacultyMembers() {
        return facultyMembers;
    }

    public void setFacultyMembers(List<FacultyProfile> facultyMembers) {
        this.facultyMembers = facultyMembers;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

}
