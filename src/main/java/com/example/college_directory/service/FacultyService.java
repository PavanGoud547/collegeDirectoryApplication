package com.example.college_directory.service;

import com.example.college_directory.DTO.FacultyDto;
import com.example.college_directory.model.Course;
import com.example.college_directory.model.FacultyProfile;
import com.example.college_directory.repository.CourseRepository;
import com.example.college_directory.repository.DepartmentRepository;
import com.example.college_directory.repository.FacultyProfileRepository;
import com.example.college_directory.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FacultyService {

    @Autowired
    private FacultyProfileRepository facultyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    // Get all faculty members returning entity FacultyProfile
    public List<FacultyProfile> getAllFacultyProfiles() {
        return facultyRepository.findAll();
    }

    // Get a faculty member by ID returning entity FacultyProfile
    public Optional<FacultyProfile> getFacultyProfileById(Long id) {
        return facultyRepository.findById(id);
    }

    // Get all faculty members returning DTOs
    public List<FacultyDto> getAllFaculty() {
        List<FacultyProfile> facultyList = facultyRepository.findAll();
        return facultyList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // Get a faculty member by ID returning DTO
    public FacultyDto getFacultyById(Long id) {
        return facultyRepository.findById(id)
                .map(this::convertToDto)
                .orElseThrow(() -> new RuntimeException("Faculty not found with ID: " + id));
    }

    // Create a new faculty member
    public FacultyDto createFaculty(FacultyDto facultyDto) {
        FacultyProfile faculty = convertToEntity(facultyDto);
        faculty = facultyRepository.save(faculty);
        return convertToDto(faculty);
    }

    // Update a faculty member by ID
    public FacultyDto updateFaculty(Long id, FacultyDto facultyDto) {
        FacultyProfile faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found with ID: " + id));

        // Set updated fields
        faculty.setSpecialization(facultyDto.getSpecialization());

        // Set associated entities
        faculty.setUser(userRepository.findById(facultyDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + facultyDto.getUserId())));
        faculty.setDepartment(departmentRepository.findById(facultyDto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + facultyDto.getDepartmentId())));

        // Update courses if necessary
        List<Course> courses = courseRepository.findAllById(facultyDto.getCourseIds());
        faculty.setCourses(courses);

        faculty = facultyRepository.save(faculty);
        return convertToDto(faculty);
    }

    // Delete a faculty member by ID
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    // Helper methods to convert between entity and DTO
    private FacultyDto convertToDto(FacultyProfile faculty) {
        Long departmentId = faculty.getDepartment() != null ? faculty.getDepartment().getId() : null;
        Long userId = faculty.getUser() != null ? faculty.getUser().getId() : null;

        List<Long> courseIds = faculty.getCourses().stream()
                .map(Course::getId)
                .collect(Collectors.toList());

        return new FacultyDto(
                faculty.getId(),
                userId,
                departmentId,
                courseIds,
                faculty.getSpecialization()
        );
    }

    private FacultyProfile convertToEntity(FacultyDto facultyDto) {
        FacultyProfile faculty = new FacultyProfile();

        // Fetch associated entities using their IDs
        faculty.setUser(userRepository.findById(facultyDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + facultyDto.getUserId())));
        faculty.setDepartment(departmentRepository.findById(facultyDto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + facultyDto.getDepartmentId())));

        List<Course> courses = courseRepository.findAllById(facultyDto.getCourseIds());
        faculty.setCourses(courses);
        faculty.setSpecialization(facultyDto.getSpecialization()); // Make sure this field exists in FacultyDto

        return faculty;
    }
}
