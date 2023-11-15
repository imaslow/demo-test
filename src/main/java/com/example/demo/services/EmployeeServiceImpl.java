package com.example.demo.services;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entities.Employee;
import com.example.demo.mappers.EmployeeMapper;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.services.interfaces.EmployeeService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;


    @Override
    public Employee saveEmployee(EmployeeDto employeeDto) {
        return employeeRepository.save(employeeMapper.convertToEmployee(employeeDto));
    }

    @Override
    public Employee updateEmployee(Long id, EmployeeDto employeeDto) {

        Employee editEmployee = employeeRepository.getEmployeeById(id);
        Employee employee = employeeMapper.convertToEmployee(employeeDto);

        editEmployee.setLastName(employee.getLastName());
        editEmployee.setFirstName(employee.getFirstName());
        editEmployee.setMiddle_name(employee.getMiddle_name());
        editEmployee.setBirthDate(employee.getBirthDate());
        editEmployee.setDepartment(employee.getDepartment());
        editEmployee.setPost(employee.getPost());

        return editEmployee;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.getEmployeeById(id);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getAllEmployeeByDepartmentAndPost(String department, String post) {
        return employeeRepository.findByDepartmentAndPost(department, post);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
