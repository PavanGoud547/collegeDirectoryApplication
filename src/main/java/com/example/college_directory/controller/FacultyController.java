package com.example.college_directory.controller;


import com.example.college_directory.DTO.FacultyDto;
import com.example.college_directory.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    @GetMapping
    public ResponseEntity<List<FacultyDto>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDto> getFacultyById(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.getFacultyById(id));
    }

    @PostMapping
    public ResponseEntity<FacultyDto> createFaculty(@RequestBody FacultyDto facultyDto) {
        return ResponseEntity.ok(facultyService.createFaculty(facultyDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyDto> updateFaculty(@PathVariable Long id, @RequestBody FacultyDto facultyDto) {
        return ResponseEntity.ok(facultyService.updateFaculty(id, facultyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok("Faculty member deleted successfully");
    }
}
