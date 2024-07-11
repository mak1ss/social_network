package com.practice.social_network.mappers;

import com.practice.social_network.dtos.user.UserRequest;
import com.practice.social_network.dtos.user.UserResponse;
import com.practice.social_network.model.User;
import com.practice.social_network.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserMapper implements Mapper<User, UserResponse, UserRequest> {

    private UserRepository userRepository;

    public User requestToEntity(UserRequest request, Optional<Integer> id) {
        User entity = new User();
        entity.setId(id.orElse(null));
        entity.setFullName(request.getFullName());
        entity.setNickname(request.getNickname());
        entity.setEmail(request.getEmail());

        if(id.isPresent()) {
            User actualEntity = userRepository.findById(id.get()).orElseThrow();
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
