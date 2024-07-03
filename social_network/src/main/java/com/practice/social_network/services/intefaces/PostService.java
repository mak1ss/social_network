package com.practice.social_network.services.intefaces;

import com.practice.social_network.dtos.postComment.PostCommentRequest;
import com.practice.social_network.dtos.post.PostRequest;

import java.util.List;

public interface PostService {

    PostRequest createPost(PostRequest post, int userId) throws IllegalArgumentException;

    PostRequest updatePost(PostRequest post, int userId) throws IllegalArgumentException;

    PostRequest deletePost(int postId, int userId) throws IllegalArgumentException;

    List<PostRequest> getUserPosts(int userId) throws IllegalArgumentException;

    List<PostRequest> getFriendsPosts(int userId, int pageNumber) throws IllegalArgumentException;

    PostRequest likePost(int userId, int postId) throws IllegalArgumentException;

    PostRequest leaveComment(String commentBody, int postId, int userId);

    List<PostCommentRequest> getPostComments(int postId, int pageNumber);
}
