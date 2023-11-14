package com.example.demo.mappers;


import com.example.demo.dto.DepartmentDto;
import com.example.demo.entities.Department;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.Optional;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartmentMapper {

    DepartmentDto convertToDepartmentDto(Optional<Department> department);

    Department convertToDepartment(DepartmentDto departmentDto);
}
