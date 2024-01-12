package com.example.demo.controllers;

import com.example.demo.controllers.interfaces.EmployeeRestApi;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.entities.Employee;
import com.example.demo.mappers.EmployeeMapper;
import com.example.demo.services.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class EmployeeRestController implements EmployeeRestApi {

    private final EmployeeService employeeService;
    private final EmployeeMapper employeeMapper;

    @Override
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        log.info("getAll: get all Employee");
        List<EmployeeDto> employees = employeeService.getAllEmployee();
        return employees.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmployeeDto> getEmployeeDtoById(Long id) {
        log.info("getById: get Employee by id. id = {}", id);
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isEmpty()) {
            log.info("getById: get Employee by id. id = {}", id);
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeMapper.convertToEmployeeDto(employee.get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmployeeDto> createEmployeeDto(EmployeeDto employeeDto) {
        log.info("create: create new Employee {}", employeeDto);
        return new ResponseEntity<>(employeeMapper.convertToEmployeeDto(employeeService.saveEmployee(employeeDto)),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<EmployeeDto> updateEmployeeDtoById(Long id, EmployeeDto employeeDto) {
        if (employeeService.getEmployeeById(id).isEmpty()) {
            log.error("update: Department with id={} doesn't exist.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("update: update Employee with id = {}", id);
        return new ResponseEntity<>(employeeMapper.convertToEmployeeDto(employeeService.updateEmployee(id, employeeDto)),
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmployeeDto> getEmployeeDtoByDepartmentAndPost(String departmentName, String postName) {
        log.info("getEmployeeDtoByDepartmentAndPost: get Employee by department = {} and post = {}", departmentName, postName);
        Employee employee = employeeService.findEmployeeByDepartmentAndPost(departmentName, postName);
        EmployeeDto employeeDto = employeeMapper.convertToEmployeeDto(employee);
        if (employee.getId() == null) {
            log.error("getEmployeeDtoByDepartmentAndPost: Employee with department = {} or post = {} not found", departmentName, postName);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteEmployeeById(Long id) {
        log.info("deleteEmployeeById: deleting a Employee with id = {}", id);
        try {
            employeeService.deleteEmployeeById(id);
        } catch (Exception e) {
            log.error("deleteEmployeeById: error of deleting - Employee with id = {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
