package com.example.demo.controllers;

import com.example.demo.controllers.interfaces.PostRestApi;
import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.PostDto;
import com.example.demo.entities.Employee;
import com.example.demo.entities.Post;
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

    @Override
    public ResponseEntity<List<Post>> getAllPosts() {
        log.info("getAll: get all Posts");
        List<Post> posts = postService.getAllPosts();
        return posts.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostDto> getPostDtoById(Long id) {
        log.info("getById: get Post by id. id = {}", id);
        Optional<Post> post = postService.getPostById(id);
        return post.isEmpty()
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(new PostDto(post.get()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostDto> createPostDto(PostDto postDto) {
        log.info("create: create new Post {}", postDto);
        return ResponseEntity.ok(new PostDto(postService.savePost(postDto)));
    }

    @Override
    public ResponseEntity<PostDto> updatePostDtoById(Long id, PostDto postDto) {
        log.info("update: update Post with id = {}", id);
        return new ResponseEntity<>(new PostDto(postService.updatePost(id, postDto)), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deletePostById(Long id) {
        log.info("deletePostById: deletePostById Post with id = {}", id);
        Optional<Post> post = postService.getPostById(id);
        if (post.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.deletePostById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
