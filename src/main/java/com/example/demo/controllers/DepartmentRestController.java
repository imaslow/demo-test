package com.example.demo.controllers;

import com.example.demo.controllers.interfaces.DepartmentRestApi;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.entities.Department;
import com.example.demo.mappers.DepartmentMapper;
import com.example.demo.services.interfaces.DepartmentService;
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
public class DepartmentRestController implements DepartmentRestApi {

    private final DepartmentService departmentService;
    private final DepartmentMapper departmentMapper;

    @Override
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        log.info("getAll: get all Departments");
        List<DepartmentDto> departments = departmentService.getAllDepartments();
        return departments.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DepartmentDto> getDepartmentDtoById(Long id) {
        log.info("getById: get Department by id. id = {}", id);
        Optional<Department> department = departmentService.getDepartmentById(id);
        if (department.isEmpty()) {
            log.info("getById: not found Department with id = {}", id);
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
                return new ResponseEntity<>(departmentMapper.convertToDepartmentDto(department.get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DepartmentDto> createDepartment(DepartmentDto departmentDto) {
        log.info("create: create new Department {}", departmentDto);
        return new ResponseEntity<>(departmentMapper.convertToDepartmentDto(departmentService.saveDepartment(departmentDto)),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DepartmentDto> updateDepartmentDtoById(Long id, DepartmentDto departmentDto) {
        if (departmentService.getDepartmentById(id).isEmpty()) {
            log.error("update: Department with id={} doesn't exist.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        log.info("update: update Department with id = {}", id);
        return new ResponseEntity<>(departmentMapper.convertToDepartmentDto(departmentService.updateDepartment(id, departmentDto)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteDepartmentById(Long id) {
        log.info("deleteDepartmentById: deleting a Department with id = {}", id);
        try {
            departmentService.deleteDepartmentById(id);
        } catch (Exception e) {
            log.error("deleteDepartmentById: error of deleting - Department with id = {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

