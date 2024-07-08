package com.practice.social_network.services.intefaces;

import com.practice.social_network.dtos.userFollow.UserFollowRequest;
import com.practice.social_network.dtos.userFollow.UserFollowResponse;

import java.util.List;

public interface UserFollowService {

    List<UserFollowResponse> getUserFollows(Integer userId);

    List<UserFollowResponse> getUserFollowers(Integer userId);

    UserFollowResponse createFollow(UserFollowRequest followRequest);

    void deleteFollow(Integer followId);


}
