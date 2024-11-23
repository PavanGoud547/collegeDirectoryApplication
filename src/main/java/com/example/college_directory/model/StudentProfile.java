package com.example.college_directory.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "student_profile")
public class StudentProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    @Column(name = "year_of_study")
    private int yearOfStudy;


    // Default constructor
    public StudentProfile() {
    }

    // Parameterized constructor
    public StudentProfile(Long id, User user, Department department, List<Enrollment> enrollments, int yearOfStudy) {
        this.id = id;
        this.user = user;
        this.department = department;
        this.enrollments = enrollments;
        this.yearOfStudy = yearOfStudy;
    }


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }

    public int getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(int yearOfStudy) {
        this.yearOfStudy = yearOfStudy;
    }

    // Additional methods for DTO conversion
    public String getName() {
        return user != null ? user.getName() : null; // Assuming User has a getName() method
    }

    public String getEmail() {
        return user != null ? user.getEmail() : null; // Assuming User has a getEmail() method
    }

    public Long getDepartmentId() {
        return department != null ? department.getId() : null; // Return department ID if exists
    }


    public void setName(String name) {
        return ;
    }

    public void setEmail(String email) {
        return;
    }
}
