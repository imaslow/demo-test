package com.example.demo.mappers;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.entities.Department;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentMapperTest {

    private final DepartmentMapper departmentMapper = Mappers.getMapper(DepartmentMapper.class);

    @Test
    public void shouldConvertToDepartmentDto() {

        Department department = new Department();
        department.setId(1L);
        department.setDepartmentName("RISKI");

        DepartmentDto departmentDto = departmentMapper.convertToDepartmentDto(department);

        assertEquals(department.getId(), departmentDto.getId());
        assertEquals(department.getDepartmentName(), departmentDto.getDepartmentName());
    }

    @Test
    public void shouldConvertToDepartment() {

        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(2L);
        departmentDto.setDepartmentName("BIGDATA");

        Department department = departmentMapper.convertToDepartment(departmentDto);

        assertEquals(departmentDto.getId(), department.getId());
        assertEquals(departmentDto.getDepartmentName(), department.getDepartmentName());

    }

}