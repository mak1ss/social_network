package com.practice.social_network.services.intefaces;

import com.practice.social_network.dtos.postComment.PostCommentRequest;
import com.practice.social_network.dtos.postComment.PostCommentResponse;

import java.util.List;

public interface PostCommentService {

    PostCommentResponse createPostComment(PostCommentRequest postCommentRequest);

    PostCommentResponse updatePostComment(PostCommentRequest postCommentRequest);

    PostCommentResponse deletePostComment(Integer postCommentId);

    PostCommentResponse getPostComment(Integer postCommentId);

    List<PostCommentResponse> getPostComments(Integer postId, Integer pageNumber);
}
