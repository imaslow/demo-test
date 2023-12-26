package com.example.demo.controllers;

import com.example.demo.controllers.interfaces.PostRestApi;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.PostDto;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Post;
import com.example.demo.mappers.PostMapper;
import com.example.demo.services.interfaces.PostService;
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
public class PostRestController implements PostRestApi {

    private final PostService postService;
    private final PostMapper postMapper;

    @Override
    public ResponseEntity<List<PostDto>> getAllPosts() {
        log.info("getAll: get all Posts");
        List<PostDto> posts = postService.getAllPosts();
        return posts.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostDto> getPostDtoById(Long id) {
        log.info("getById: get Post by id. id = {}", id);
        Optional<Post> post = postService.getPostById(id);
        if (post.isEmpty()) {
            log.info("getById: not found Post by id. id = {}", id);
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postMapper.convertToPostDto(post.get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostDto> createPostDto(PostDto postDto) {
        log.info("create: create new Post {}", postDto);
        return new ResponseEntity<>(postMapper.convertToPostDto(postService.savePost(postDto)),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PostDto> updatePostDtoById(Long id, PostDto postDto) {
        if (postService.getPostById(id).isEmpty()) {
            log.error("update: Department with id={} doesn't exist.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        log.info("update: update Post with id = {}", id);
        return new ResponseEntity<>(postMapper.convertToPostDto(postService.updatePost(id, postDto)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deletePostById(Long id) {
        log.info("deletePostById: deleting a Post with id = {}", id);
        try {
            postService.deletePostById(id);
        } catch (Exception e) {
            log.error("deletePostById: error of deleting - Post with id = {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
