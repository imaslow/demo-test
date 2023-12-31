package com.example.demo.services;

import com.example.demo.dto.PostDto;
import com.example.demo.entities.Post;
import com.example.demo.mappers.PostMapper;
import com.example.demo.repositories.PostRepository;
import com.example.demo.services.interfaces.PostService;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    @Override
    public Post savePost(PostDto postDto) {
        return postRepository.save(postMapper.convertToPost(postDto));
    }

    @Override
    public Post updatePost(Long id, PostDto postDto) {

        Post editPost = postMapper.convertToPost(postDto);
        editPost.setId(id);
        if(editPost.getPostName() == null) {
            editPost.setPostName(postRepository.findById(id).get().getPostName());
        }
        return postRepository.save(editPost);
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postMapper::convertToPostDto).collect(Collectors.toList());
    }

    @Override
    public void deletePostById(Long id) {
        postRepository.deleteById(id);
    }
}
