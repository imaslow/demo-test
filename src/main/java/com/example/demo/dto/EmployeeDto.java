package com.example.demo.dto;

import com.example.demo.entities.Department;
import com.example.demo.entities.Post;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {

    private Long id;

    private String lastName;

    private String firstName;

    private String middle_name;

    private LocalDate birthDate;

    private Department department;

    private Post post;

}
