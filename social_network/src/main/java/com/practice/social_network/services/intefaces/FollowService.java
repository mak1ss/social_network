package com.practice.social_network.services.intefaces;

import com.practice.social_network.dtos.follow.FollowRequest;
import com.practice.social_network.dtos.follow.FollowResponse;

import java.util.List;

public interface FollowService {

    List<FollowResponse> getUserFollows(Integer userId);

    List<FollowResponse> getUserFollowers(Integer userId);

    FollowResponse createFollow(FollowRequest followRequest);

    void deleteFollow(Integer followId);


}
