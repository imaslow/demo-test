package com.example.demo.mappers;


import com.example.demo.dto.EmployeeDto;
import com.example.demo.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper {

    EmployeeDto convertToEmployeeDto(Employee employee);

    Employee convertToEmployee(EmployeeDto employeeDto);
}
