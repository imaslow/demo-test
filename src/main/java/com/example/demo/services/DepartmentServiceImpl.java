package com.example.demo.services;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entities.Department;
import com.example.demo.mappers.DepartmentMapper;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.services.interfaces.DepartmentService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    @Override
    public Department saveDepartment(DepartmentDto departmentDto) {
        return departmentRepository.save(departmentMapper.convertToDepartment(departmentDto));
    }

    @Override
    public Department updateDepartment(Long id, DepartmentDto departmentDto) {

        Department editDepartment = departmentRepository.getDepartmentById(id);
        Department department = departmentMapper.convertToDepartment(departmentDto);

        editDepartment.setDepartmentName(department.getDepartmentName());

        return editDepartment;
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.getDepartmentById(id);
    }

    @Override
    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }

    @Override
    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }
}
