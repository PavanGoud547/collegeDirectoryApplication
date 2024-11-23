package com.example.college_directory.service;

import com.example.college_directory.DTO.CourseDto;
import com.example.college_directory.model.Course;
import com.example.college_directory.model.Department;
import com.example.college_directory.repository.CourseRepository;
import com.example.college_directory.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public CourseDto createCourse(CourseDto courseDto) {
        Course course = new Course();
        course.setName(courseDto.getName());
        course.setDescription(courseDto.getDescription());
        Department department = departmentRepository.findByName(courseDto.getDepartmentName());
        if (department == null) {
            throw new RuntimeException("Department not found");
        }
        course.setDepartment(department);

        course = courseRepository.save(course);
        return convertToDto(course);
    }

    public CourseDto updateCourse(Long courseId, CourseDto courseDto) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            course.setName(courseDto.getName());
            course.setDescription(courseDto.getDescription());
            Department department = departmentRepository.findByName(courseDto.getDepartmentName());
            if (department == null) {
                throw new RuntimeException("Department not found");
            }
            course.setDepartment(department);

            course = courseRepository.save(course);
            return convertToDto(course);
        }
        return null;
    }

    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public CourseDto getCourseById(Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        return courseOptional.map(this::convertToDto).orElse(null);
    }

    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }

    private CourseDto convertToDto(Course course) {
        return new CourseDto(
                course.getId(),
                course.getName(),
                course.getDescription(),
                course.getDepartment().getName()
        );
    }
}
