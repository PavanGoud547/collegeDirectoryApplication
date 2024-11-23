package com.example.college_directory.service;

import com.example.college_directory.DTO.StudentDto;
import com.example.college_directory.model.StudentProfile;
import com.example.college_directory.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentProfileRepository studentRepository;

    // Get a list of all students returning DTOs
    public List<StudentDto> getAllStudents() {
        List<StudentProfile> students = studentRepository.findAll();
        return students.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // Get a single student by ID returning DTO
    public StudentDto getStudentById(Long id) {
        StudentProfile student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + id));
        return convertToDto(student);
    }

    // Create or update student profile
    public StudentDto saveOrUpdateStudent(StudentDto studentDto) {
        StudentProfile studentProfile = convertToEntity(studentDto);
        studentProfile = studentRepository.save(studentProfile);
        return convertToDto(studentProfile);
    }

    // Delete a student by ID
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
    }

    // Helper methods to convert between entity and DTO
    private StudentDto convertToDto(StudentProfile student) {
        return new StudentDto(
                student.getId(),
                student.getName(),
                student.getEmail(),
                student.getDepartmentId()  // Assuming StudentProfile has a getDepartmentId() method
                // Add other fields as needed
        );
    }

    private StudentProfile convertToEntity(StudentDto studentDto) {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setId(studentDto.getId());
        studentProfile.setName(studentDto.getName());
        studentProfile.setEmail(studentDto.getEmail());
        // Set other fields as needed
        return studentProfile;
    }

    public StudentDto createStudent(StudentDto studentDto) {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setId(studentDto.getId());
        studentProfile.setName(studentDto.getName());
        studentProfile.setEmail(studentDto.getEmail());
        // Set other fields as needed
        return new StudentDto();
    }

    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        StudentProfile studentProfile = new StudentProfile();
        studentProfile.setId(studentDto.getId());
        studentProfile.setName(studentDto.getName());
        studentProfile.setEmail(studentDto.getEmail());
        // Set other fields as needed
        return studentDto;
    }
}
