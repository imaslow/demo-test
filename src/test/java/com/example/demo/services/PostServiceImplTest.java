package com.example.demo.services;

import com.example.demo.dto.PostDto;
import com.example.demo.entities.Post;
import com.example.demo.mappers.PostMapper;
import com.example.demo.repositories.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
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
    private PostMapper postMapper;

    @Test
    void savePost() {

        Post post = new Post();
        PostDto postDto = new PostDto();

        when(postMapper.convertToPost(postDto)).thenReturn(post);
        when(postRepository.save(post)).thenReturn(post);

        Post savePost = postService.savePost(postDto);

        assertNotNull(savePost);
        assertEquals(post, savePost);
        verify(postMapper, times(1)).convertToPost(postDto);
        verify(postRepository, times(1)).save(post);

    }

    @Test
    void updatePost() {
        Long id = 1L;
        PostDto postDto = new PostDto();
        postDto.setId(id);
        postDto.setPostName("MIDDLE");

        Post existingPost = new Post();
        existingPost.setId(id);
        existingPost.setPostName("JUNIOR");

        Post updatedPost = new Post();
        updatedPost.setId(id);
        updatedPost.setPostName("MIDDLE");

        when(postMapper.convertToPost(postDto)).thenReturn(updatedPost);
        when(postRepository.save(updatedPost)).thenReturn(updatedPost);

        Post result = postService.updatePost(id, postDto);

        assertEquals(updatedPost.getPostName(), result.getPostName());
        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(postRepository, times(1)).save(updatedPost);
        verify(postMapper, times(1)).convertToPost(postDto);

    }

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

        Post post1 = new Post(1L, "JUNIOR");
        Post post2 = new Post(2L, "MIDDLE");
        List<Post> posts = Arrays.asList(post1, post2);

        when(postRepository.findAll()).thenReturn(posts);

        PostDto postDto1 = new PostDto(1L, "JUNIOR_DTO");
        PostDto postDto2 = new PostDto(2L, "MIDDLE_DTO");
        List<PostDto> expectedPostDtos = Arrays.asList(postDto1, postDto2);

        when(postMapper.convertToPostDto(post1)).thenReturn(postDto1);
        when(postMapper.convertToPostDto(post2)).thenReturn(postDto2);

        List<PostDto> actualPostDtos = postService.getAllPosts();

        assertNotNull(actualPostDtos);
        assertEquals(expectedPostDtos.size(), actualPostDtos.size());
        assertEquals(expectedPostDtos.get(0).getPostName(), actualPostDtos.get(0).getPostName());
        assertEquals(expectedPostDtos.get(1).getPostName(), actualPostDtos.get(1).getPostName());
        verify(postRepository, times(1)).findAll();
        verify(postMapper, times(2)).convertToPostDto(any());

    }

    @Test
    void deletePostById() {
        Long departmentId = 2L;

        postService.deletePostById(departmentId);

        verify(postRepository, times(1)).deleteById(departmentId);
    }
}