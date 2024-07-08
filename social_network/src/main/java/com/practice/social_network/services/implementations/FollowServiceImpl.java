package com.practice.social_network.services.implementations;

import com.practice.social_network.dtos.follow.FollowRequest;
import com.practice.social_network.dtos.follow.FollowResponse;
import com.practice.social_network.entities.Follow;
import com.practice.social_network.mappers.FollowMapper;
import com.practice.social_network.repositories.FollowRepository;
import com.practice.social_network.repositories.UserRepository;
import com.practice.social_network.services.intefaces.FollowService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FollowServiceImpl implements FollowService {

    private FollowRepository followRepository;
    private UserRepository userRepository;
    private FollowMapper followMapper;

    @Override
    public List<FollowResponse> getUserFollows(Integer userId) {
        if(!userRepository.existsById(userId)) {
           throw new IllegalArgumentException("User not found");
        }

        return followMapper.entitiesToListResponse(followRepository.findByFollowerId(userId));
    }

    @Override
    public List<FollowResponse> getUserFollowers(Integer userId) {
        if(!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("User not found");
        }

        return followMapper.entitiesToListResponse(followRepository.findByFollowedId(userId));
    }

    @Override
    public FollowResponse createFollow(FollowRequest followRequest) {
        Follow entity = followMapper.requestToEntity(followRequest);

        entity = followRepository.save(entity);

        return followMapper.entityToResponse(entity);
    }

    @Override
    public void deleteFollow(Integer followId) {
        Follow entity = followRepository.findById(followId).orElseThrow(
                () -> new IllegalArgumentException("Follow not found")
        );

        followRepository.delete(entity);
    }
}
