package com.blog.blog.Service.impl;

import com.blog.blog.Dto.PostDto;
import com.blog.blog.Dto.UserDto;
import com.blog.blog.Entity.Post;
import com.blog.blog.Entity.User;
import com.blog.blog.Repository.PostRepository;
import com.blog.blog.Repository.PostResponse;
import com.blog.blog.Repository.UserRepository;
import com.blog.blog.Service.PostService;
import com.blog.blog.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public PostDto createPost(PostDto postDto, Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUser(user);
        Post newPost = postRepository.save(post);
        return convertToDto(newPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepository.save(post);
        return convertToDto(updatedPost);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", postId));
        postRepository.delete(post);
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> allPosts = postRepository.findAll();
            return allPosts.stream()
                .map(this::convertToDto)
                 .collect(Collectors.toList());}

    @Override
    public PostDto getPostById(Integer id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Post id", id));

        return convertToDto(post);
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
        List<Post> posts = postRepository.findByUser(user);
        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
private PostDto convertToDto(Post post) {
    PostDto postDto = new PostDto();
    postDto.setId(post.getId());
    postDto.setTitle(post.getTitle());
    postDto.setContent(post.getContent());
    if (post.getUser() != null) {
        UserDto userDto = new UserDto();
        userDto.setId(post.getUser().getId());
        userDto.setName(post.getUser().getName());
        userDto.setEmail(post.getUser().getEmail());
        postDto.setUser(userDto);
    }
    return postDto;
}
}