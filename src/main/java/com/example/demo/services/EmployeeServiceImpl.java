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
import java.util.stream.Collectors;

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

        Employee editEmployee = employeeRepository.findById(id).get();
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
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::convertToEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public Employee findEmployeeByDepartmentAndPost(String departmentName, String postName) {
        return employeeRepository.findEmployeeByDepartmentDepartmentNameAndPostPostName(departmentName, postName);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
