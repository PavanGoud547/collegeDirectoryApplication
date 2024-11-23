package com.example.college_directory.controller;

import com.example.college_directory.DTO.AuthRequest;
import com.example.college_directory.DTO.AuthResponseDTO;
import com.example.college_directory.security.JwtManager;
import com.example.college_directory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtManager jwtManager;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) {
        userService.register(authRequest);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody AuthRequest authRequest) {
        try {
            // Authenticate user credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponseDTO("Invalid credentials"));
        }

        // Generate JWT token
        String token = jwtManager.generateToken(authRequest.getUsername());
        return ResponseEntity.ok(new AuthResponseDTO(token));
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestBody String token) {
        try {
            // Extract username from the token and validate
            String username = jwtManager.extractUsername(token);
            boolean isValid = jwtManager.validateToken(token, username);
            return ResponseEntity.ok(isValid ? "Token is valid" : "Token is invalid");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token is invalid");
        }
    }

    @GetMapping("/public")
    public ResponseEntity<String> publicAccess() {
        return ResponseEntity.ok("Public access");
    }

    @GetMapping("/protected")
    @PreAuthorize("isAuthenticated()") // Allow access for authenticated users
    public ResponseEntity<String> protectedAccess() {
        return ResponseEntity.ok("Protected access");
    }

}
