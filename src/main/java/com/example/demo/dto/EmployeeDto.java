package com.example.demo.dto;

import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDto {

    private Long id;

    private String lastName;

    private String firstName;

    private String middle_name;

    private LocalDate birthDate;

    private Department department;

    private Post post;

//    public EmployeeDto(Employee employee) {
//    }
}
