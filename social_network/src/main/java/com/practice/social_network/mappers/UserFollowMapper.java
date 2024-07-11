package com.practice.social_network.mappers;

import com.practice.social_network.dtos.userFollow.UserFollowRequest;
import com.practice.social_network.dtos.userFollow.UserFollowResponse;
import com.practice.social_network.model.UserFollow;
import com.practice.social_network.repositories.UserRepository;
import com.practice.social_network.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserFollowMapper implements Mapper<UserFollow, UserFollowResponse, UserFollowRequest> {

    private UserService userService;
    private UserMapper userMapper;

    @Override
    public UserFollow requestToEntity(UserFollowRequest request, Optional<Integer> id) {
        UserFollow entity = new UserFollow();
        entity.setId(id.orElse(null));
        entity.setFollower(userService.getById(request.getFollowerId()).orElseThrow());
        entity.setFollowed(userService.getById(request.getFollowedId()).orElseThrow());
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
