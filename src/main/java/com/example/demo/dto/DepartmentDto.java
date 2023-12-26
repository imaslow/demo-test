package com.example.demo.dto;

import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentDto {

    private Long id;

    private String departmentName;

//    public DepartmentDto(Department department) {
//        this.id = department.getId();
//        this.departmentName = department.getDepartmentName();
//    }
}
