package com.practice.social_network.mappers;

import com.practice.social_network.dtos.userFollow.UserFollowRequest;
import com.practice.social_network.dtos.userFollow.UserFollowResponse;
import com.practice.social_network.entities.UserFollow;
import com.practice.social_network.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class UserFollowMapper implements Mapper<UserFollow, UserFollowResponse, UserFollowRequest> {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public UserFollow requestToEntity(UserFollowRequest request) {
        UserFollow entity = new UserFollow();
        entity.setId(request.getId());
        entity.setFollower(userRepository.findById(request.getFollowerId()).orElseThrow(
                () -> new IllegalArgumentException("Follower user not found")
        ));
        entity.setFollowed(userRepository.findById(request.getFollowedId()).orElseThrow(
                () -> new IllegalArgumentException("Followed user not found")
        ));
        entity.setSubscriptionDate(LocalDateTime.now());

        return entity;
    }

    @Override
    public UserFollowResponse entityToResponse(UserFollow entity) {
        UserFollowResponse response = new UserFollowResponse();
        response.setId(entity.getId());
        response.setFollower(userMapper.entityToResponse(entity.getFollower()));
        response.setFollowed(userMapper.entityToResponse(entity.getFollowed()));
        response.setSubscriptionDate(entity.getSubscriptionDate());
        return response;
    }

    @Override
    public List<UserFollowResponse> entitiesToListResponse(Collection<UserFollow> entityList) {
        return entityList.stream().map(this::entityToResponse).toList();
    }
}
