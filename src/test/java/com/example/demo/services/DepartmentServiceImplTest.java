package com.example.demo.services;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entities.Department;
import com.example.demo.entities.Post;
import com.example.demo.mappers.DepartmentMapper;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.services.interfaces.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
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

    @Mock
    private DepartmentMapper departmentMapper;

    @Test
    void saveDepartment() {

        DepartmentDto departmentDto = new DepartmentDto();
        Department department = new Department();

        when(departmentMapper.convertToDepartment(departmentDto)).thenReturn(department);
        when(departmentRepository.save(department)).thenReturn(department);

        Department savedDepartment = departmentService.saveDepartment(departmentDto);

        assertNotNull(savedDepartment);
        assertEquals(department, savedDepartment);
        verify(departmentMapper, times(1)).convertToDepartment(departmentDto);
        verify(departmentRepository, times(1)).save(department);

    }

    @Test
    void updateDepartment() {

        Long id = 1L;
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(id);
        departmentDto.setDepartmentName("TECH");

        Department existingDepartment = new Department();
        existingDepartment.setId(id);
        existingDepartment.setDepartmentName("RISKI");

        Department updatedDepartment = new Department();
        updatedDepartment.setId(id);
        updatedDepartment.setDepartmentName("TECH");

        when(departmentMapper.convertToDepartment(departmentDto)).thenReturn(updatedDepartment);
        when(departmentRepository.save(updatedDepartment)).thenReturn(updatedDepartment);

        Department result = departmentService.updateDepartment(id, departmentDto);

        assertEquals(updatedDepartment.getDepartmentName(), result.getDepartmentName());
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(departmentRepository, times(1)).save(updatedDepartment);
        verify(departmentMapper, times(1)).convertToDepartment(departmentDto);

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

        Department department1 = new Department(1L, "RISKI");
        Department department2 = new Department(2L, "BIGDATA");
        List<Department> departments = Arrays.asList(department1, department2);

        when(departmentRepository.findAll()).thenReturn(departments);

        DepartmentDto departmentDto1 = new DepartmentDto(1L, "RISKI_DTO");
        DepartmentDto departmentDto2 = new DepartmentDto(2L, "BIGDATA_DTO");
        List<DepartmentDto> expectedDepartmentDtos = Arrays.asList(departmentDto1, departmentDto2);

        when(departmentMapper.convertToDepartmentDto(department1)).thenReturn(departmentDto1);
        when(departmentMapper.convertToDepartmentDto(department2)).thenReturn(departmentDto2);

        List<DepartmentDto> actualDepartmentDtos = departmentService.getAllDepartments();

        assertNotNull(actualDepartmentDtos);
        assertEquals(expectedDepartmentDtos.size(), actualDepartmentDtos.size());
        assertEquals(expectedDepartmentDtos.get(0).getDepartmentName(), actualDepartmentDtos.get(0).getDepartmentName());
        assertEquals(expectedDepartmentDtos.get(1).getDepartmentName(), actualDepartmentDtos.get(1).getDepartmentName());
        verify(departmentRepository, times(1)).findAll();
        verify(departmentMapper, times(2)).convertToDepartmentDto(any());

    }

    @Test
    void deleteDepartmentById() {
        Long departmentId = 2L;

        departmentService.deleteDepartmentById(departmentId);

        verify(departmentRepository, times(1)).deleteById(departmentId);
    }

}
