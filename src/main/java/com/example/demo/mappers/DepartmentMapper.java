package com.example.demo.mappers;


import com.example.demo.dto.DepartmentDto;
import com.example.demo.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartmentMapper {

    DepartmentDto convertToDepartmentDto(Department department);

    Department convertToDepartment(DepartmentDto departmentDto);
}
