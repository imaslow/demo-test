package com.example.demo.services.interfaces;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entities.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {

    Department saveDepartment(DepartmentDto departmentDto);

    Department updateDepartment(Long id, DepartmentDto departmentDto);

    Optional<Department> getDepartmentById(Long id);

    List<Department> getAllDepartment();

    void deleteDepartmentById(Long id);

}
