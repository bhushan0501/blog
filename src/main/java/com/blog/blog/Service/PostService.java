package com.blog.blog.Service;

import com.blog.blog.Dto.PostDto;
import com.blog.blog.Entity.Post;
import com.blog.blog.Repository.PostResponse;

import java.util.List;


    public interface PostService {
        // Create
        PostDto createPost(PostDto postDto, Integer userId);

        // Update
        PostDto updatePost(PostDto postDto, Integer postId);

        // Delete
        void deletePost(Integer postId);

        // Get all posts
        List<PostDto> getAllPosts();

        // Get single post by ID
        PostDto getPostById(Integer id);

        // Get all posts by user
        List<PostDto> getPostByUser(Integer userId);


    }

