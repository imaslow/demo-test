package com.example.demo.controllers;

import com.example.demo.IntegrationTestBase;
import com.example.demo.dto.DepartmentDto;
import com.example.demo.mappers.DepartmentMapper;
import com.example.demo.repositories.DepartmentRepository;
import com.example.demo.services.interfaces.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;
import static org.testcontainers.shaded.org.hamcrest.Matchers.equalTo;

@Sql({"/sqlQuery/delete-from-tables.sql"})
@Sql(value = {"/sqlQuery/create-department-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class DepartmentRestControllerIT extends IntegrationTestBase {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentService departmentService;

    @Test
    void shouldGetAllDepartments() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/department"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetDepartmentById() throws Exception {
        Long id = 3L;
        mockMvc.perform(get("http://localhost:8080/api/department/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper
                        .writeValueAsString(departmentService.getDepartmentById(id).get())));
    }

    @Test
    void shouldCreateDepartment() throws Exception {
        DepartmentDto departmentDto = new DepartmentDto(4L, "TEST_DEPARTMENT");
        System.out.println(objectMapper.writeValueAsString(departmentDto));
        mockMvc.perform(post("http://localhost:8080/api/department")
                    .content(objectMapper.writeValueAsString(departmentDto))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(departmentDto)));
    }

    @Test
    void shouldUpdateDepartmentById() throws Exception {
        Long id = 3L;
        DepartmentDto updatableDepartment = departmentMapper.convertToDepartmentDto(departmentService.getDepartmentById(id).get());
        updatableDepartment.setDepartmentName("TEST_DEPARTMENT");
        long numberOfDepartment = departmentRepository.count();

        mockMvc.perform(patch("http://localhost:8080/api/department/{id}", id)
                .content(objectMapper.writeValueAsString(updatableDepartment))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatableDepartment)))
                .andExpect(result -> assertThat(departmentRepository.count(), equalTo(numberOfDepartment)));
    }

    @Test
    void shouldDeleteDepartmentById() throws Exception {
        Long id = 3L;
        mockMvc.perform(delete("http://localhost:8080/api/department/{id}", id))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}