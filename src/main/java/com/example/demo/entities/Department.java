package com.example.demo.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_department")
    @SequenceGenerator(name = "seq_department", initialValue = 10, allocationSize = 1)
    private Long id;

    @Column(name = "department_name")
    private String departmentName;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Employee> employeeList;

    public Department(long id, String departmentName) {
    }
}
