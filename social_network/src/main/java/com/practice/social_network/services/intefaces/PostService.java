package com.practice.social_network.services.intefaces;

import com.practice.social_network.dtos.post.PostResponse;
import com.practice.social_network.dtos.post.PostRequest;

import java.util.List;

public interface PostService {

    PostResponse createPost(PostRequest post) throws IllegalArgumentException;

    PostResponse updatePost(PostRequest post) throws IllegalArgumentException;

    void deletePost(Integer postId) throws IllegalArgumentException;

    List<PostResponse> getUserPosts(Integer userId) throws IllegalArgumentException;

}
