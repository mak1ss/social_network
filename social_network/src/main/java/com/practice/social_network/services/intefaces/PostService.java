package com.practice.social_network.services.intefaces;

import com.practice.social_network.dtos.PostCommentDTO;
import com.practice.social_network.dtos.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO post, int userId) throws IllegalArgumentException;

    PostDTO updatePost(PostDTO post, int userId) throws IllegalArgumentException;

    PostDTO deletePost(int postId, int userId) throws IllegalArgumentException;

    List<PostDTO> getUserPosts(int userId) throws IllegalArgumentException;

    List<PostDTO> getFriendsPosts(int userId, int pageNumber) throws IllegalArgumentException;

    PostDTO likePost(int userId, int postId) throws IllegalArgumentException;

    PostDTO leaveComment(String commentBody, int postId, int userId);

    List<PostCommentDTO> getPostComments(int postId, int pageNumber);
}
