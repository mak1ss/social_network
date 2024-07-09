package com.practice.social_network.services.intefaces;

import com.practice.social_network.dtos.postLike.PostLikeRequest;
import com.practice.social_network.dtos.postLike.PostLikeResponse;

public interface PostLikeService {

    PostLikeResponse createPostLike(PostLikeRequest request);
    void deletePostLike(PostLikeRequest request);
}
