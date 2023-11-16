package com.example.demo.entities;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_name")
    private String postName;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Employee> postList;

}
