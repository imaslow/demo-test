package com.example.demo.controllers;

import com.example.demo.controllers.interfaces.DepartmentRestApi;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.entities.Department;
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

    @Override
    public ResponseEntity<List<Department>> getAllDepartments() {
        log.info("getAll: get all Departments");
        List<Department> departments = departmentService.getAllDepartment();
        return departments.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DepartmentDto> getDepartmentDtoById(Long id) {
        log.info("getById: get Department by id. id = {}", id);
        Optional<Department> department = departmentService.getDepartmentById(id);
        return department.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(new DepartmentDto(department.get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DepartmentDto> createDepartmentDto(DepartmentDto departmentDto) {
        log.info("create: create new Department {}", departmentDto);
        return ResponseEntity.ok(new DepartmentDto(departmentService.saveDepartment(departmentDto)));
    }

    @Override
    public ResponseEntity<DepartmentDto> updateDepartmentDtoById(Long id, DepartmentDto departmentDto) {
        log.info("update: update Department with id = {}", id);
        return new ResponseEntity<>(new DepartmentDto(departmentService.updateDepartment(id, departmentDto)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteDepartmentById(Long id) {
        log.info("deleteDepartmentById: deleteDepartmentById Department with id = {}", id);
        Optional<Department> department = departmentService.getDepartmentById(id);
        if (department.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        departmentService.deleteDepartmentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}

