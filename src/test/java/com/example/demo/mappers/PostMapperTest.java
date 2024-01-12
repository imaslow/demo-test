package com.example.demo.mappers;

import com.example.demo.dto.PostDto;
import com.example.demo.entities.Post;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import static org.junit.jupiter.api.Assertions.*;

class PostMapperTest {

    private final PostMapper postMapper = Mappers.getMapper(PostMapper.class);

    @Test
    public void shouldConvertToPostDto() {

        Post post = new Post();
        post.setId(1L);
        post.setPostName("JUNIOR");

        PostDto postDto = postMapper.convertToPostDto(post);

        assertEquals(post.getId(), postDto.getId());
        assertEquals(post.getPostName(), postDto.getPostName());
    }

    @Test
    public void shouldConvertToPost() {

        PostDto postDto = new PostDto();
        postDto.setId(2L);
        postDto.setPostName("MIDDLE");

        Post post = postMapper.convertToPost(postDto);

        assertEquals(postDto.getId(), post.getId());
        assertEquals(postDto.getPostName(), post.getPostName());

    }
}
