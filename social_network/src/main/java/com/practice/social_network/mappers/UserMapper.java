package com.practice.social_network.mappers;

import com.practice.social_network.dtos.user.UserRequest;
import com.practice.social_network.dtos.user.UserResponse;
import com.practice.social_network.entities.User;
import com.practice.social_network.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class UserMapper implements Mapper<User, UserResponse, UserRequest> {

    private UserRepository userRepository;

    public User requestToEntity(UserRequest request) {
        User entity = new User();
        entity.setId(request.getId());
        entity.setFullName(request.getFullName());
        entity.setNickname(request.getNickname());
        entity.setEmail(request.getEmail());

        if(entity.getId() != null) {
            User actualEntity = userRepository.findById(entity.getId()).orElseThrow();
            entity.setFollowings(actualEntity.getFollowings());
            entity.setPassword(actualEntity.getPassword());
        } else {
            entity.setPassword(request.getPassword());
        }

        return entity;
    }

    public UserResponse entityToResponse(User entity) {
        UserResponse response = new UserResponse();
        response.setId(entity.getId());
        response.setFullName(entity.getFullName());
        response.setNickname(entity.getNickname());
        response.setEmail(entity.getEmail());

        return response;
    }

    public List<UserResponse> entitiesToListResponse(Collection<User> entityList) {
        return entityList.stream().map(this::entityToResponse).toList();
    }
}
