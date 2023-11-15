package com.example.demo.services;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entities.Department;
import com.example.demo.repositories.DepartmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;
    @Test
    void saveDepartment() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName("RISKI");

        Department department = new Department();
        department.setDepartmentName("RISKI");

        when(departmentService.saveDepartment(departmentDto)).thenReturn(department);

        Department saveDepartment = departmentService.saveDepartment(departmentDto);

        assertNotNull(saveDepartment);
        assertEquals(department.getDepartmentName(), saveDepartment.getDepartmentName());
    }

    @Test
    void updateDepartment() {
        Long departmentId = 1L;
        String departmentName = "TECH";

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setDepartmentName(departmentName);

        Department existingDepartment = new Department();
        existingDepartment.setId(departmentId);
        existingDepartment.setDepartmentName("RISKI");

        Department updateDepartment = new Department();
        updateDepartment.setId(departmentId);
        updateDepartment.setDepartmentName(departmentName);

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(existingDepartment));
        when(departmentRepository.save(existingDepartment)).thenReturn(updateDepartment);

        Department result = departmentService.updateDepartment(departmentId, departmentDto);

        assertNotNull(result);
        assertEquals(departmentId, result.getId());
        assertEquals(departmentName, result.getDepartmentName());
    }

    @Test
    void getDepartmentById() {
        Long departmentId = 1L;
        String departmentName = "RISKI";

        Department department = new Department();
        department.setId(departmentId);
        department.setDepartmentName(departmentName);

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));

        Optional<Department> foundDepartment = departmentService.getDepartmentById(departmentId);

        assertNotNull(foundDepartment);
        assertEquals(departmentId, foundDepartment.get().getId());
        assertEquals(departmentName, foundDepartment.get().getDepartmentName());
    }

    @Test
    void getAllDepartment() {
        List<Department> departments = new ArrayList<>();
        departments.add(new Department(0L, "RISKI"));
        departments.add(new Department(1L, "BIGDATA"));
        departments.add(new Department(2L, "TECH"));
        when(departmentService.getAllDepartment()).thenReturn(departments);

        List<Department> result = departmentRepository.findAll();

        assertEquals(departments.size(), result.size());
        assertEquals(departments.get(0).getDepartmentName(), result.get(0).getDepartmentName());
        assertEquals(departments.get(1).getDepartmentName(), result.get(1).getDepartmentName());
        assertEquals(departments.get(2).getDepartmentName(), result.get(2).getDepartmentName());
    }

    @Test
    void deleteDepartmentById() {
        Long departmentId = 2L;

        Department existingDepartment = new Department();
        existingDepartment.setId(departmentId);
        existingDepartment.setDepartmentName("TECH");

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(existingDepartment));

        departmentService.deleteDepartmentById(departmentId);

        verify(departmentRepository, times(2)).delete(existingDepartment);
    }
}