package com.practice.social_network.services.intefaces;

import com.practice.social_network.dtos.postLike.PostLikeRequest;
import com.practice.social_network.dtos.postLike.PostLikeResponse;

import java.util.List;

public interface PostLikeService {

    PostLikeResponse createPostLike(PostLikeRequest request);
    List<PostLikeResponse> getPostLikes(Integer postId);
    void deletePostLike(PostLikeRequest request);
}
