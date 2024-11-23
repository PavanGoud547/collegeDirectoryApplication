package com.example.college_directory.DTO;

public class AuthResponseDTO {
    private String jwt;

    public void AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    public AuthResponseDTO(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
