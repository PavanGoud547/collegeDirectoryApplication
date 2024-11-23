package com.example.college_directory.service;

import com.example.college_directory.model.AdministratorProfile;
import com.example.college_directory.model.User;
import com.example.college_directory.repository.AdminRepository;
import com.example.college_directory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Get all admin profiles
    public List<AdministratorProfile> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Get an admin by ID
    public Optional<AdministratorProfile> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

    // Create or update admin profile
    public AdministratorProfile saveOrUpdateAdmin(AdministratorProfile adminProfile) {
        return adminRepository.save(adminProfile);
    }

    // Delete an admin profile by ID
    public void deleteAdmin(Long id) {
        adminRepository.deleteById(id);
    }

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();  // Fetch all users from the UserRepository
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}