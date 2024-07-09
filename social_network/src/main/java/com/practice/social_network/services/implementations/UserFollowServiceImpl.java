package com.practice.social_network.services.implementations;

import com.practice.social_network.dtos.userFollow.UserFollowRequest;
import com.practice.social_network.dtos.userFollow.UserFollowResponse;
import com.practice.social_network.entities.UserFollow;
import com.practice.social_network.mappers.UserFollowMapper;
import com.practice.social_network.repositories.UserFollowRepository;
import com.practice.social_network.repositories.UserRepository;
import com.practice.social_network.services.intefaces.UserFollowService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserFollowServiceImpl implements UserFollowService {

    private UserFollowRepository followRepository;
    private UserRepository userRepository;
    private UserFollowMapper followMapper;

    @Override
    public List<UserFollowResponse> getUserFollows(Integer userId) {
        if(!userRepository.existsById(userId)) {
           throw new IllegalArgumentException("User not found");
        }

        return followMapper.entitiesToListResponse(followRepository.findByFollowerId(userId));
    }

    @Override
    public List<UserFollowResponse> getUserFollowers(Integer userId) {
        if(!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found");
        }

        return followMapper.entitiesToListResponse(followRepository.findByFollowedId(userId));
    }

    @Override
    public UserFollowResponse createFollow(UserFollowRequest followRequest) {
        UserFollow entity = followMapper.requestToEntity(followRequest);

        entity = followRepository.save(entity);

        return followMapper.entityToResponse(entity);
    }

    @Override
    public void deleteFollow(Integer followId) {
        UserFollow entity = followRepository.findById(followId).orElseThrow(
                () -> new IllegalArgumentException("Follow not found")
        );

        followRepository.delete(entity);
    }
}
