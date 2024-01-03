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
import java.util.stream.Collectors;

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
        Department editDepartment = departmentMapper.convertToDepartment(departmentDto);
        editDepartment.setId(id);
        if(editDepartment.getDepartmentName() == null) {
            editDepartment.setDepartmentName(departmentRepository.findById(id).get().getDepartmentName());
        }
        return departmentRepository.save(editDepartment);
    }

    @Override
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::convertToDepartmentDto).collect(Collectors.toList());
    }

    @Override
    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }
}
