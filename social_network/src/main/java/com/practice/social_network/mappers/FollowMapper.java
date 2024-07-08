package com.practice.social_network.mappers;

import com.practice.social_network.dtos.follow.FollowRequest;
import com.practice.social_network.dtos.follow.FollowResponse;
import com.practice.social_network.entities.Follow;
import com.practice.social_network.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class FollowMapper implements Mapper<Follow, FollowResponse, FollowRequest> {

    private UserRepository userRepository;
    private UserMapper userMapper;

    @Override
    public Follow requestToEntity(FollowRequest request) {
        Follow entity = new Follow();
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
    public FollowResponse entityToResponse(Follow entity) {
        FollowResponse response = new FollowResponse();
        response.setId(entity.getId());
        response.setFollower(userMapper.entityToResponse(entity.getFollower()));
        response.setFollowed(userMapper.entityToResponse(entity.getFollowed()));
        response.setSubscriptionDate(entity.getSubscriptionDate());
        return response;
    }

    @Override
    public List<FollowResponse> entitiesToListResponse(Collection<Follow> entityList) {
        return entityList.stream().map(this::entityToResponse).toList();
    }
}
