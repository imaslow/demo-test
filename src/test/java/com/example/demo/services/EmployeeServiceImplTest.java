package com.example.demo.services;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Post;
import com.example.demo.mappers.EmployeeMapper;
import com.example.demo.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeMapper employeeMapper;

    @Test
    void saveEmployee() {

        EmployeeDto employeeDto = new EmployeeDto();
        Employee employee = new Employee();

        when(employeeMapper.convertToEmployee(employeeDto)).thenReturn(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employeeDto);

        assertNotNull(savedEmployee);
        assertEquals(employee, savedEmployee);
        verify(employeeMapper, times(1)).convertToEmployee(employeeDto);
        verify(employeeRepository, times(1)).save(employee);

    }

    @Test
    void updateEmployee() {

        Long employeeId = 1L;

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employeeId);
        employeeDto.setFirstName("IVAN");
        employeeDto.setLastName("IVANOV");
        employeeDto.setMiddle_name("IVANOVICH");
        employeeDto.setBirthDate(LocalDate.parse("1993-01-12"));

        Employee existingEmployee = new Employee();
        existingEmployee.setId(employeeId);
        existingEmployee.setFirstName("OLEG");
        existingEmployee.setLastName("SIDOROV");
        existingEmployee.setMiddle_name("NIKOLAEVICH");
        existingEmployee.setBirthDate(LocalDate.parse("1990-11-02"));

        Employee updateEmployee = new Employee();
        updateEmployee.setId(employeeId);
        updateEmployee.setFirstName("IVAN");
        updateEmployee.setLastName("IVANOV");
        updateEmployee.setMiddle_name("IVANOVICH");
        updateEmployee.setBirthDate(LocalDate.parse("1993-01-12"));

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(employeeMapper.convertToEmployee(employeeDto)).thenReturn(updateEmployee);

        Employee result = employeeService.updateEmployee(employeeId, employeeDto);

        assertNotNull(result);
        assertEquals(updateEmployee.getId(), result.getId());
        assertEquals(updateEmployee.getFirstName(), result.getFirstName());
        assertEquals(updateEmployee.getLastName(), result.getLastName());
        assertEquals(updateEmployee.getMiddle_name(), result.getMiddle_name());
        assertEquals(updateEmployee.getBirthDate(), result.getBirthDate());
        verify(employeeMapper, times(1)).convertToEmployee(employeeDto);

    }



    @Test
    void getEmployeeById() {
        Long employeeId = 1L;
        String firstName = "IVAN";
        String lastName = "IVANOV";
        String middleName = "IVANOVICH";
        LocalDate birthDate = LocalDate.parse("1993-01-12");

        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setMiddle_name(middleName);
        employee.setBirthDate(birthDate);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        Optional<Employee> foundEmployee = employeeService.getEmployeeById(employeeId);

        assertNotNull(foundEmployee);
        assertEquals(employeeId, foundEmployee.get().getId());
        assertEquals(firstName, foundEmployee.get().getFirstName());
        assertEquals(lastName, foundEmployee.get().getLastName());
        assertEquals(middleName, foundEmployee.get().getMiddle_name());
        assertEquals(birthDate, foundEmployee.get().getBirthDate());
    }


    @Test
    void getAllEmployee() {

        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setFirstName("IGOR");
        employee1.setLastName("KAPRANOV");
        employee1.setMiddle_name("NICOLAEVICH");
        employee1.setBirthDate(LocalDate.parse("1993-01-12"));

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setFirstName("PETR");
        employee2.setLastName("PETROV");
        employee2.setMiddle_name("PETROVICH");
        employee2.setBirthDate(LocalDate.parse("1995-01-12"));

        List<Employee> employeeList = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(employeeList);

        EmployeeDto employeeDto1 = new EmployeeDto();
        employeeDto1.setId(1L);
        employeeDto1.setFirstName("IGOR_DTO");
        employeeDto1.setLastName("KAPRANOV_DTO");
        employeeDto1.setMiddle_name("NICOLAEVICH_DTO");
        employeeDto1.setBirthDate(LocalDate.parse("1993-01-12"));

        EmployeeDto employeeDto2 = new EmployeeDto();
        employeeDto2.setId(2L);
        employeeDto2.setFirstName("PETR_DTO");
        employeeDto2.setLastName("PETROV_DTO");
        employeeDto2.setMiddle_name("PETROVICH_DTO");
        employeeDto2.setBirthDate(LocalDate.parse("1995-01-12"));

        List<EmployeeDto> expectedEmployeeListDto = Arrays.asList(employeeDto1, employeeDto2);

        when(employeeMapper.convertToEmployeeDto(employee1)).thenReturn(employeeDto1);
        when(employeeMapper.convertToEmployeeDto(employee2)).thenReturn(employeeDto2);

        List<EmployeeDto> actualEmployeeListDto = employeeService.getAllEmployee();

        assertNotNull(actualEmployeeListDto);
        assertEquals(expectedEmployeeListDto.size(), actualEmployeeListDto.size());
        assertEquals(expectedEmployeeListDto.get(0).getFirstName(), actualEmployeeListDto.get(0).getFirstName());
        assertEquals(expectedEmployeeListDto.get(0).getLastName(), actualEmployeeListDto.get(0).getLastName());
        assertEquals(expectedEmployeeListDto.get(0).getMiddle_name(), actualEmployeeListDto.get(0).getMiddle_name());
        assertEquals(expectedEmployeeListDto.get(0).getBirthDate(), actualEmployeeListDto.get(0).getBirthDate());
        assertEquals(expectedEmployeeListDto.get(1).getFirstName(), actualEmployeeListDto.get(1).getFirstName());
        assertEquals(expectedEmployeeListDto.get(1).getLastName(), actualEmployeeListDto.get(1).getLastName());
        assertEquals(expectedEmployeeListDto.get(1).getMiddle_name(), actualEmployeeListDto.get(1).getMiddle_name());
        assertEquals(expectedEmployeeListDto.get(1).getBirthDate(), actualEmployeeListDto.get(1).getBirthDate());
        verify(employeeRepository, times(1)).findAll();
        verify(employeeMapper, times(2)).convertToEmployeeDto(any());
    }

    @Test
    void getAllEmployeeByDepartmentAndPost() {
        String department = "RISKI";
        String post = "JUNIOR";

        Department expectedDepartment = new Department();
        expectedDepartment.setId(1L);
        expectedDepartment.setDepartmentName(department);

        Post expectedPost = new Post();
        expectedPost.setId(1L);
        expectedPost.setPostName(post);

        Employee expectedEmployee = new Employee();
        expectedEmployee.setId(1L);
        expectedEmployee.setFirstName("Sasha");
        expectedEmployee.setLastName("Sidorova");
        expectedEmployee.setMiddle_name("Olegovna");
        expectedEmployee.setBirthDate(LocalDate.parse("1990-01-23"));
        expectedEmployee.setDepartment(expectedDepartment);
        expectedEmployee.setPost(expectedPost);

        when(employeeRepository.findEmployeeByDepartmentDepartmentNameAndPostPostName(department, post))
                        .thenReturn(expectedEmployee);

        Employee actualEmployee = employeeService.findEmployeeByDepartmentAndPost(department, post);

        verify(employeeRepository, times(1)).findEmployeeByDepartmentDepartmentNameAndPostPostName(department, post);
        assertNotNull(actualEmployee);
        assertEquals(expectedEmployee, actualEmployee);

    }

    @Test
    void deleteEmployeeById() {
        Long employeeId = 2L;

        employeeService.deleteEmployeeById(employeeId);

        verify(employeeRepository, times(1)).deleteById(employeeId);
    }
}