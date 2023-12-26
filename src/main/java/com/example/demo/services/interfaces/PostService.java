package com.example.demo.services.interfaces;

import com.example.demo.dto.PostDto;
import com.example.demo.entities.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post savePost(PostDto postDto);

    Post updatePost(Long id, PostDto postDto);

    Optional<Post> getPostById(Long id);

    List<PostDto> getAllPosts();

    void deletePostById(Long id);
}
