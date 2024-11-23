package com.example.college_directory.service;

import com.example.college_directory.DTO.DepartmentDto;
import com.example.college_directory.model.Department;
import com.example.college_directory.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setDescription(departmentDto.getDescription());
        department = departmentRepository.save(department);
        return convertToDto(department);
    }

    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (departmentOptional.isPresent()) {
            Department department = departmentOptional.get();
            department.setName(departmentDto.getName());
            department.setDescription(departmentDto.getDescription());
            department = departmentRepository.save(department);
            return convertToDto(department);
        }
        return null;
    }

    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public DepartmentDto getDepartmentById(Long departmentId) {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        return departmentOptional.map(this::convertToDto).orElse(null);
    }

    public void deleteDepartment(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }

    private DepartmentDto convertToDto(Department department) {
        return new DepartmentDto(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
    }
}
