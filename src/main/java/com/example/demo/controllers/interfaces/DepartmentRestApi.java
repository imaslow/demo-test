package com.example.demo.controllers.interfaces;

import com.example.demo.dto.DepartmentDto;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Department REST")
@RequestMapping("/api/department")
public interface DepartmentRestApi {

    @ApiOperation(value = "Get list of all Department")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Department found"),
            @ApiResponse(code = 400, message = "Department not found")
    })
    @GetMapping
    ResponseEntity<List<DepartmentDto>> getAllDepartments();

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Department by \"id\"")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Department found"),
            @ApiResponse(code = 404, message = "Department not found")})
    ResponseEntity<DepartmentDto> getDepartmentDtoById(
            @ApiParam(
                    name = "id",
                    value = "Department.id"
            )
            @PathVariable Long id);

    @PostMapping
    @ApiOperation(value = "Create Department")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Department created")})
    ResponseEntity<DepartmentDto> createDepartment(
            @ApiParam(
                    name = "Department",
                    value = "Department model"
            )
            @RequestBody DepartmentDto departmentDto);

    @PatchMapping("/{id}")
    @ApiOperation(value = "Update Department by \"id\"")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Department updated"),
            @ApiResponse(code = 404, message = "Department not found")})
    ResponseEntity<DepartmentDto> updateDepartmentDtoById(
            @ApiParam(
                    name = "id",
                    value = "Department.id"
            )
            @PathVariable("id") Long id,
            @ApiParam(
                    name = "DepartmentDto",
                    value = "Department model"
            )
            @RequestBody DepartmentDto departmentDto);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Department by \"id\"")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Department deleted"),
            @ApiResponse(code = 404, message = "Department not found")})
    ResponseEntity<HttpStatus> deleteDepartmentById(
            @ApiParam(
                    name = "id",
                    value = "Department.id"
            )
            @PathVariable Long id);
}
