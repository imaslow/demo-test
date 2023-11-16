package com.example.demo.services;

import com.example.demo.dto.DepartmentDto;
import com.example.demo.dto.PostDto;
import com.example.demo.entities.Department;
import com.example.demo.entities.Post;
import com.example.demo.mappers.DepartmentMapper;
import com.example.demo.mappers.PostMapper;
import com.example.demo.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostServiceImpl postService;

    @Mock
    private final PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    @Test
    void savePost() {

        Post post = new Post();
        post.setPostName("JUNIOR");

        PostDto postDto = postMapper.convertToPostDto(post);

        when(postService.savePost(postDto)).thenReturn(post);

        Post savePost = postService.savePost(postDto);

        assertNotNull(savePost);
        assertEquals(post.getPostName(), savePost.getPostName());
    }

//    @Test
//    void updatePost() {
//        Long postId = 1L;
//        String postName = "MIDDLE";
//
////        PostDto postDto = new PostDto();
////        postDto.setPostName(postName);
//
//        Post existingPost = new Post();
//        existingPost.setId(postId);
//        existingPost.setPostName("JUNIOR");
//
//        Post updatePost = new Post();
//        updatePost.setId(postId);
//        updatePost.setPostName(postName);
//
//        PostDto postDto = postMapper.convertToPostDto(updatePost);
//
//        when(postRepository.findById(postId)).thenReturn(Optional.of(existingPost));
//        when(postRepository.save(existingPost)).thenReturn(updatePost);
//
//        Post result = postService.updatePost(postId, postDto);
//
//        assertNotNull(result);
//        assertEquals(postId, result.getId());
//        assertEquals(postName, result.getPostName());
//    }

    @Test
    void getPostById() {
        Long postId = 1L;
        String postName = "MIDDLE";

        Post post = new Post();
        post.setId(postId);
        post.setPostName(postName);

        when(postRepository.findById(postId)).thenReturn(Optional.of(post));

        Optional<Post> foundPost = postService.getPostById(postId);

        assertNotNull(foundPost);
        assertEquals(postId, foundPost.get().getId());
        assertEquals(postName, foundPost.get().getPostName());
    }

    @Test
    void getAllPosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post(0L, "JUNIOR"));
        posts.add(new Post(1L, "MIDDLE"));
        posts.add(new Post(2L, "SENIOR"));
        when(postService.getAllPosts()).thenReturn(posts);

        List<Post> result = postRepository.findAll();

        assertEquals(posts.size(), result.size());
        assertEquals(posts.get(0).getPostName(), result.get(0).getPostName());
        assertEquals(posts.get(1).getPostName(), result.get(1).getPostName());
        assertEquals(posts.get(2).getPostName(), result.get(2).getPostName());
    }

    @Test
    void deletePostById() {
        Long departmentId = 2L;

        postService.deletePostById(departmentId);

        verify(postRepository, times(1)).deleteById(departmentId);
    }
}