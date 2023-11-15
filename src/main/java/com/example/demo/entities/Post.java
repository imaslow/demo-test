package com.example.demo.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_post")
    @SequenceGenerator(name = "seq_post", initialValue = 10, allocationSize = 1)
    private Long id;

    @Column(name = "postName")
    private String postName;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Employee> postList;
}
