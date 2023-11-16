package com.example.demo.controllers;

import com.example.demo.controllers.interfaces.EmployeeRestApi;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Post;
import com.example.demo.services.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
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

    @Override
    public ResponseEntity<List<Employee>> getAllEmployee() {
        log.info("getAll: get all Employee");
        List<Employee> employees = employeeService.getAllEmployee();
        return employees.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmployeeDto> getEmployeeDtoById(Long id) {
        log.info("getById: get Employee by id. id = {}", id);
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(value -> new ResponseEntity<>(new EmployeeDto(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<EmployeeDto> createEmployeeDto(EmployeeDto employeeDto) {
        log.info("create: create new Employee {}", employeeDto);
        return ResponseEntity.ok(new EmployeeDto(employeeService.saveEmployee(employeeDto)));
    }

    @Override
    public ResponseEntity<EmployeeDto> updateEmployeeDtoById(Long id, EmployeeDto employeeDto) {
        log.info("update: update Employee with id = {}", id);
        return new ResponseEntity<>(new EmployeeDto(employeeService.updateEmployee(id, employeeDto)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Employee>> getEmployeeDtoByDepartmentAndPost(String departmentName, String postName) {
        List<Employee> employees;
        if (departmentName == null && postName == null) {
            employees = employeeService.getAllEmployee();
            log.info("getAll: get all Employee");
        } else {
            log.info("filter: filter Employee by department and post");
            employees = employeeService.getAllEmployeeByDepartmentAndPost(departmentName, postName);
        }
        log.info("employee isEmpty");
        return employees.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteEmployeeById(Long id) {
        log.info("deleteEmployeeById: deleteEmployeeById Employee with id = {}", id);
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
