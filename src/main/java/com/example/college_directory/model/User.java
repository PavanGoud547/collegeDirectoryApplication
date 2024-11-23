package com.example.college_directory.model;

import jakarta.persistence.*;
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false) // Adjust nullable as needed
    private String name; // New field for the user's name

    @Column(nullable = false) // Adjust nullable as needed
    private String email; // New field for the user's email

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name; // Updated to return the actual name
    }

    public void setName(String name) {
        this.name = name; // Added setter for name
    }

    public String getEmail() {
        return email; // Updated to return the actual email
    }

    public void setEmail(String email) {
        this.email = email; // Added setter for email
    }
}

