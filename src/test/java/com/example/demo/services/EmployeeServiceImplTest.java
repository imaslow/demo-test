package com.example.demo.services;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.PostDto;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Post;
import com.example.demo.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    private final LocalDate birthDateIvan = LocalDate.parse("1993-01-12");
    private final LocalDate birthDatePetr = LocalDate.parse("1997-10-11");
    private final LocalDate birthDateOleg = LocalDate.parse("1990-11-02");
    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void saveEmployee() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("PETR");
        employeeDto.setLastName("PETROV");
        employeeDto.setMiddle_name("PETROVICH");
        employeeDto.setBirthDate(LocalDate.parse("1997-10-11"));

        Employee employee = new Employee();
        employee.setFirstName("PETR");
        employee.setLastName("PETROV");
        employee.setMiddle_name("PETROVICH");
        employee.setBirthDate(LocalDate.parse("1997-10-11"));

        when(employeeService.saveEmployee(employeeDto)).thenReturn(employee);

        Employee saveEmployee = employeeService.saveEmployee(employeeDto);

        assertNotNull(saveEmployee);
        assertEquals(employee.getFirstName(), saveEmployee.getFirstName());
        assertEquals(employee.getLastName(), saveEmployee.getLastName());
        assertEquals(employee.getMiddle_name(), saveEmployee.getMiddle_name());
        assertEquals(employee.getBirthDate(), saveEmployee.getBirthDate());
    }

    @Test
    void updateEmployee() {
        Long employeeId = 1L;
        String firstName = "IVAN";
        String lastName = "IVANOV";
        String middleName = "IVANOVICH";
        LocalDate birthDate = LocalDate.parse("1993-01-12");

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName(firstName);
        employeeDto.setLastName(lastName);
        employeeDto.setMiddle_name(middleName);
        employeeDto.setBirthDate(birthDate);

        Employee existingEmployee = new Employee();
        existingEmployee.setId(employeeId);
        existingEmployee.setFirstName("OLEG");
        existingEmployee.setLastName("SIDOROV");
        existingEmployee.setMiddle_name("NIKOLAEVICH");
        existingEmployee.setBirthDate(LocalDate.parse("1990-11-02"));

        Employee updateEmployee = new Employee();
        updateEmployee.setId(employeeId);
        updateEmployee.setFirstName(firstName);
        updateEmployee.setLastName(lastName);
        updateEmployee.setMiddle_name(middleName);
        updateEmployee.setBirthDate(birthDate);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(employeeRepository.save(existingEmployee)).thenReturn(updateEmployee);

        Employee result = employeeService.updateEmployee(employeeId, employeeDto);

        assertNotNull(result);
        assertEquals(employeeId, result.getId());
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
        assertEquals(middleName, result.getMiddle_name());
        assertEquals(birthDate, result.getBirthDate());
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

        Employee foundEmployee = employeeService.getEmployeeById(employeeId);

        assertNotNull(foundEmployee);
        assertEquals(employeeId, foundEmployee.getId());
        assertEquals(firstName, foundEmployee.getFirstName());
        assertEquals(lastName, foundEmployee.getLastName());
        assertEquals(middleName, foundEmployee.getMiddle_name());
        assertEquals(birthDate, foundEmployee.getBirthDate());
    }

    @Test
    void getAllEmployee() {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(0L, "IVANOV", "IVAN", "IVANOVICH", birthDateIvan));
        employees.add(new Employee(1L, "PETROV", "PETR", "PETROVICH", birthDatePetr));
        employees.add(new Employee(2L, "SIDOROV", "OLEG", "NIKOLAEVICH", birthDateOleg));
        when(employeeService.getAllEmployee()).thenReturn(employees);

        List<Employee> result = employeeRepository.findAll();

        assertEquals(employees.size(), result.size());
        assertEquals(employees.get(0).getFirstName(), result.get(0).getFirstName());
        assertEquals(employees.get(0).getLastName(), result.get(0).getLastName());
        assertEquals(employees.get(0).getMiddle_name(), result.get(0).getMiddle_name());
        assertEquals(employees.get(1).getFirstName(), result.get(1).getFirstName());
        assertEquals(employees.get(1).getLastName(), result.get(1).getLastName());
        assertEquals(employees.get(1).getMiddle_name(), result.get(1).getMiddle_name());
        assertEquals(employees.get(2).getFirstName(), result.get(2).getFirstName());
        assertEquals(employees.get(2).getLastName(), result.get(2).getLastName());
        assertEquals(employees.get(2).getMiddle_name(), result.get(2).getMiddle_name());
    }

    @Test
    void getAllEmployeeByDepartmentAndPost() {
        String department = "TECH";
        String post = "JUNIOR";

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(0L, "IVANOV", "IVAN", "IVANOVICH", birthDateIvan));
        employees.add(new Employee(1L, "PETROV", "PETR", "PETROVICH", birthDatePetr));
        employees.add(new Employee(2L, "SIDOROV", "OLEG", "NIKOLAEVICH", birthDateOleg));
        when(employeeService.getAllEmployee()).thenReturn(employees);

        List<Employee> result = employeeRepository.findByDepartmentAndPost(department, post);

        assertEquals(employees.size(), result.size());
        assertEquals(employees.get(0).getFirstName(), result.get(0).getFirstName());
        assertEquals(employees.get(0).getLastName(), result.get(0).getLastName());
        assertEquals(employees.get(0).getMiddle_name(), result.get(0).getMiddle_name());
        assertEquals(employees.get(1).getFirstName(), result.get(1).getFirstName());
        assertEquals(employees.get(1).getLastName(), result.get(1).getLastName());
        assertEquals(employees.get(1).getMiddle_name(), result.get(1).getMiddle_name());
        assertEquals(employees.get(2).getFirstName(), result.get(2).getFirstName());
        assertEquals(employees.get(2).getLastName(), result.get(2).getLastName());
        assertEquals(employees.get(2).getMiddle_name(), result.get(2).getMiddle_name());
    }


    @Test
    void deleteEmployeeById() {
        Long employeeId = 2L;

        Employee existingEmployee = new Employee();
        existingEmployee.setId(employeeId);
        existingEmployee.setFirstName("OLEG");
        existingEmployee.setLastName("SIDOROV");
        existingEmployee.setMiddle_name("NIKOLAEVICH");
        existingEmployee.setBirthDate(birthDateOleg);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(existingEmployee));

        employeeService.deleteEmployeeById(employeeId);

        verify(employeeRepository, times(2)).delete(existingEmployee);
    }
}