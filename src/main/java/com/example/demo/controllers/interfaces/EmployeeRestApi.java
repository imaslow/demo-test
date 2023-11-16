package com.example.demo.controllers.interfaces;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Post;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Employee REST")
@RequestMapping("/api/employee")
public interface EmployeeRestApi {

    @ApiOperation(value = "Get list of all Employee")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee found"),
            @ApiResponse(code = 400, message = "Employee not found")
    })
    @GetMapping
    ResponseEntity<List<Employee>> getAllEmployee();

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Employee by \"id\"")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee found"),
            @ApiResponse(code = 404, message = "Employee not found")})
    ResponseEntity<EmployeeDto> getEmployeeDtoById(
            @ApiParam(
                    name = "id",
                    value = "Employee.id"
            )
            @PathVariable Long id);

    @PostMapping
    @ApiOperation(value = "Create Employee")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Employee created")})
    ResponseEntity<EmployeeDto> createEmployeeDto(
            @ApiParam(
                    name = "Employee",
                    value = "Employee model"
            )
            @RequestBody EmployeeDto employeeDto);

    @PatchMapping("/{id}")
    @ApiOperation(value = "Update Employee by \"id\"")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee updated"),
            @ApiResponse(code = 404, message = "Employee not found")})
    ResponseEntity<EmployeeDto> updateEmployeeDtoById(
            @ApiParam(
                    name = "id",
                    value = "Employee.id"
            )
            @PathVariable("id") Long id,
            @ApiParam(
                    name = "EmployeeDto",
                    value = "Employee model"
            )
            @RequestBody EmployeeDto employeeDto);

    @ApiOperation(value = "Get Employee by department and post")
    @ApiResponse(code = 200, message = "Found the employee")
    @GetMapping("/filtered")
    ResponseEntity<List<Employee>> getEmployeeDtoByDepartmentAndPost(
            @RequestParam(value = "departmentName", required = false) String departmentName,
            @RequestParam(value = "postName", required = false) String postName);




    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Employee by \"id\"")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Employee deleted"),
            @ApiResponse(code = 404, message = "Employee not found")})
    ResponseEntity<Void> deleteEmployeeById(
            @ApiParam(
                    name = "id",
                    value = "Employee.id"
            )
            @PathVariable Long id);
}
