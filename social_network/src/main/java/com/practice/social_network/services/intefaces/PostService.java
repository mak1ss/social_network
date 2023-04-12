package com.practice.social_network.services.intefaces;

import com.practice.social_network.entities.Post;

import java.util.List;

public interface PostService {

    Post createPost(Post post, int userId) throws IllegalArgumentException;

    Post updatePost(Post post, int userId) throws IllegalArgumentException;

    Post deletePost(int postId, int userId) throws IllegalArgumentException;

    List<Post> getUserPosts(int userId) throws IllegalArgumentException;

    List<Post> getFriendsPosts(int userId, int pageNumber) throws IllegalArgumentException;
}
