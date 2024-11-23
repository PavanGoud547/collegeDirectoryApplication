package com.example.college_directory.service;

import com.example.college_directory.DTO.AuthRequest;
import com.example.college_directory.model.User;
import com.example.college_directory.repository.UserRepository;
import com.example.college_directory.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService implements UserDetailsService {
    private final BCryptPasswordEncoder passwordEncoder;
    @Autowired
    public UserService(BCryptPasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    public String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
    public boolean isPasswordMatch(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    // Method to authenticate the user
    public String authenticateUser(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetails userDetails = loadUserByUsername(username);
        return jwtUtil.generateToken(String.valueOf(userDetails));
    }

    // Register new users
    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public User findById(Long id) {
        return userRepository.findById(id) // Find user by ID
                .orElseThrow(() -> new RuntimeException("User not found")); // Handle user not found case
    }


    public void register(AuthRequest authRequest) {
        User user = new User();
        user.setUsername(authRequest.getUsername());
        user.setPassword(encodePassword(authRequest.getPassword())); // Ensure you hash the password
        userRepository.save(user);
    }

    public User login(AuthRequest authRequest) {
        User user = userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found")); // Handle user not found
        if (isPasswordMatch(authRequest.getPassword(), user.getPassword())) {
            return registerUser(user); // Implement token generation logic
        } else {
            throw new RuntimeException("Invalid password"); // Handle invalid password
        }
    }
}