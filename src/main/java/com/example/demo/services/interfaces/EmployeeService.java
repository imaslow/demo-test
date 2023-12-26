package com.example.demo.services.interfaces;


import com.example.demo.dto.EmployeeDto;
import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Post;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(EmployeeDto employeeDto);

    Employee updateEmployee(Long id, EmployeeDto employeeDto);

    Optional<Employee> getEmployeeById(Long id);

    List<EmployeeDto> getAllEmployee();

    Employee findEmployeeByDepartmentAndPost(String departmentName, String postName);

    void deleteEmployeeById(Long id);
}
