package com.example.demo.mappers;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entities.Employee;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {

    private final EmployeeMapper employeeMapper = Mappers.getMapper(EmployeeMapper.class);

    @Test
    public void shouldConvertToEmployeeDto() {

        Employee employee = new Employee();
        employee.setId(4L);
        employee.setFirstName("OLEG");
        employee.setMiddle_name("PETROVICH");
        employee.setLastName("ZYATEV");
        employee.setBirthDate(LocalDate.parse("1995-01-20"));

        EmployeeDto employeeDto = employeeMapper.convertToEmployeeDto(employee);

        assertEquals(employee.getId(), employeeDto.getId());
        assertEquals(employee.getFirstName(), employeeDto.getFirstName());
        assertEquals(employee.getLastName(), employeeDto.getLastName());
        assertEquals(employee.getMiddle_name(), employeeDto.getMiddle_name());
        assertEquals(employee.getBirthDate(), employeeDto.getBirthDate());
    }

    @Test
    public void shouldConvertToEmployee() {

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(2L);
        employeeDto.setFirstName("IGOR");
        employeeDto.setMiddle_name("ALEKSANDROVICH");
        employeeDto.setLastName("NIKOLAEV");
        employeeDto.setBirthDate(LocalDate.parse("1958-07-24"));

        Employee employee = employeeMapper.convertToEmployee(employeeDto);

        assertEquals(employeeDto.getId(), employee.getId());
        assertEquals(employeeDto.getFirstName(), employee.getFirstName());
        assertEquals(employeeDto.getLastName(), employee.getLastName());
        assertEquals(employeeDto.getMiddle_name(), employee.getMiddle_name());
        assertEquals(employeeDto.getBirthDate(), employee.getBirthDate());
    }

}