package com.example.demo.mappers;

import com.example.demo.dto.PostDto;
import com.example.demo.entities.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {

    PostDto convertToPostDto(Post post);

    Post convertToPost(PostDto postDto);
}
