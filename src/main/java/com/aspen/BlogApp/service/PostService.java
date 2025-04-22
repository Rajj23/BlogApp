package com.aspen.BlogApp.service;

import com.aspen.BlogApp.dto.PostDto;

import java.util.List;

public interface PostService {

    public PostDto createPost(PostDto postDto,int userId,int categoryId);
    public PostDto updatePost(PostDto postDto,int id);
    public PostDto getPostById(int id);
    public List<PostDto> getAllPost();
    public void delete(int id);
    public List<PostDto> getPostByCategory(int id);
    public List<PostDto> getPostByUser(int id);

}
