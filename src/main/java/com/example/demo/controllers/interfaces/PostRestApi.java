package com.example.demo.controllers.interfaces;

import com.example.demo.dto.PostDto;
import com.example.demo.entities.Post;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "Post REST")
@RequestMapping("/api/post")
public interface PostRestApi {

    @ApiOperation(value = "Get list of all Post")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Post found"),
            @ApiResponse(code = 400, message = "Post not found")
    })
    @GetMapping
    ResponseEntity<List<Post>> getAllPosts();

    @GetMapping("/{id}")
    @ApiOperation(value = "Get Post by \"id\"")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Post found"),
            @ApiResponse(code = 404, message = "Post not found")})
    ResponseEntity<PostDto> getPostDtoById(
            @ApiParam(
                    name = "id",
                    value = "Post.id"
            )
            @PathVariable Long id);

    @PostMapping
    @ApiOperation(value = "Create Post")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Post created")})
    ResponseEntity<PostDto> createPostDto(
            @ApiParam(
                    name = "Post",
                    value = "Post model"
            )
            @RequestBody PostDto postDto);

    @PatchMapping("/{id}")
    @ApiOperation(value = "Update Post by \"id\"")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Post updated"),
            @ApiResponse(code = 404, message = "Post not found")})
    ResponseEntity<PostDto> updatePostDtoById(
            @ApiParam(
                    name = "id",
                    value = "Post.id"
            )
            @PathVariable("id") Long id,
            @ApiParam(
                    name = "PostDto",
                    value = "Post model"
            )
            @RequestBody PostDto postDto);

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Post by \"id\"")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Post deleted"),
            @ApiResponse(code = 404, message = "Post not found")})
    ResponseEntity<Void> deletePostById(
            @ApiParam(
                    name = "id",
                    value = "Post.id"
            )
            @PathVariable Long id);
}
