package com.example.demo.dto;

import com.example.demo.entities.Employee;
import com.example.demo.entities.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

    private Long id;

    private String postName;

//    private List<Employee> postList;

//    public PostDto(Post post) {
//    }
}
