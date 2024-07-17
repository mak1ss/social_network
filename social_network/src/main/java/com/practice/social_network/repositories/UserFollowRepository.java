package com.practice.social_network.repositories;

import com.practice.social_network.model.UserFollow;

import java.util.List;

public interface UserFollowRepository extends BaseCRUDRepository<UserFollow> {

    List<UserFollow> findByFollowerId(Integer followerId);

    List<UserFollow> findByFollowedId(Integer followedId);
}
