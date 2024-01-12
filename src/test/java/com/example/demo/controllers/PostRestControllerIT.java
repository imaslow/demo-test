package com.example.demo.controllers;

import com.example.demo.IntegrationTestBase;
import com.example.demo.dto.PostDto;
import com.example.demo.mappers.PostMapper;
import com.example.demo.repositories.PostRepository;
import com.example.demo.services.interfaces.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;
import static org.testcontainers.shaded.org.hamcrest.Matchers.equalTo;

@Sql({"/sqlQuery/delete-from-tables.sql"})
@Sql(value = {"/sqlQuery/create-post-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class PostRestControllerIT extends IntegrationTestBase {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostService postService;

    @Test
    void shouldGetAllPosts() throws Exception {
        mockMvc.perform(get("http://localhost:8080/api/post"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void shouldGetPostById() throws Exception {
        Long id = 2L;
        mockMvc.perform(get("http://localhost:8080/api/post/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper
                        .writeValueAsString(postService.getPostById(id).get())));
    }

    @Test
    void shouldCreatePost() throws Exception {
        PostDto postDto = new PostDto(4L, "TEST_POST");
        System.out.println(objectMapper.writeValueAsString(postDto));
        mockMvc.perform(post("http://localhost:8080/api/post")
                        .content(objectMapper.writeValueAsString(postDto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(postDto)));
    }

    @Test
    void shouldUpdatePostById() throws Exception {
        Long id = 3L;
        PostDto updatablePost = postMapper.convertToPostDto(postService.getPostById(id).get());
        updatablePost.setPostName("TEST_POST");
        long numberOfPost = postRepository.count();

        mockMvc.perform(patch("http://localhost:8080/api/post/{id}", id)
                        .content(objectMapper.writeValueAsString(updatablePost))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(updatablePost)))
                .andExpect(result -> assertThat(postRepository.count(), equalTo(numberOfPost)));
    }

    @Test
    void shouldDeletePostById() throws Exception {
        Long id = 1L;
        mockMvc.perform(delete("http://localhost:8080/api/post/{id}", id))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}