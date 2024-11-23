package com.example.college_directory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class CollegeDirectoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(CollegeDirectoryApplication.class, args);
	}

}
