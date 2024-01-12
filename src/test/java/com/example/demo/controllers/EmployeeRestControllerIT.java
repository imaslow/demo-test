package com.example.demo.controllers;

import com.example.demo.IntegrationTestBase;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.entities.Department;
import com.example.demo.entities.Post;
import com.example.demo.mappers.EmployeeMapper;
import com.example.demo.repositories.EmployeeRepository;
import com.example.demo.services.interfaces.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;
import static org.testcontainers.shaded.org.hamcrest.Matchers.equalTo;

@Sql({"/sqlQuery/delete-from-tables.sql"})
@Sql(value = {"/sqlQuery/create-employee-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class EmployeeRestControllerIT extends IntegrationTestBase {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Test
    void shouldGetAllEmployee() throws Exception{
        mockMvc.perform(get("http://localhost:8080/api/employee"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetEmployeeById() throws Exception {
        Long id = 1L;
        mockMvc.perform(get("http://localhost:8080/api/employee/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper
                        .writeValueAsString(employeeService.getEmployeeById(id).get())));
    }

    @Test
    void shouldCreateEmployee() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(4L);
        employeeDto.setFirstName("GALYA");
        employeeDto.setMiddle_name("PETROVICH");
        employeeDto.setLastName("HRENOVA");
        employeeDto.setBirthDate(LocalDate.parse("1993-01-12"));
        employeeDto.setDepartment(new Department(1L, "RISKI"));
        employeeDto.setPost(new Post(1L, "JUNIOR"));

        System.out.println(objectMapper.writeValueAsString(employeeDto));
        mockMvc.perform(post("http://localhost:8080/api/employee")
                        .content(objectMapper.writeValueAsString(employeeDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(employeeDto)));
    }

    @Test
    void shouldUpdateEmployeeById() throws Exception {
        Long id = 3L;
        EmployeeDto updatableEmployee = employeeMapper.convertToEmployeeDto(employeeService.getEmployeeById(id).get());
        updatableEmployee.setFirstName("TEST_NAME");
        long numberOfEmployee = employeeRepository.count();

        mockMvc.perform(patch("http://localhost:8080/api/employee/{id}", id)
                        .content(objectMapper.writeValueAsString(updatableEmployee))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatableEmployee)))
                .andExpect(result -> assertThat(employeeRepository.count(), equalTo(numberOfEmployee)));
    }

    @Test
    void shouldGetEmployeeByDepartmentAndPost() throws Exception {
        String departmentName = "RISKI";
        String postName = "JUNIOR";
        mockMvc.perform(get("http://localhost:8080/api/employee/filtered")
                .param("departmentName", departmentName)
                .param("postName", postName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(employeeService
                        .findEmployeeByDepartmentAndPost(departmentName, postName))));
    }

    @Test
    void shouldDeleteEmployeeById() throws Exception {
        Long id = 3L;
        mockMvc.perform(delete("http://localhost:8080/api/employee/{id}", id))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}