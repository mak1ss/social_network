package com.network.mappers;

import com.network.model.User;
import com.network.dtos.user.UserRequest;
import com.network.dtos.user.UserResponse;
import com.network.model.base.Role;
import com.network.repositories.UserRepository;
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
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setNickname(request.getNickname());
        entity.setEmail(request.getEmail());

        if(id.isPresent()) {
            User actualEntity = userRepository.findById(id.get()).orElseThrow();
            entity.setPassword(actualEntity.getPassword());
            entity.setRole(actualEntity.getRole());
        } else {
            entity.setPassword(request.getPassword());
            entity.setRole(Role.ROLE_USER);
        }

        entity.setArchived(false);

        return entity;
    }

    public UserResponse entityToResponse(User entity) {
        UserResponse response = new UserResponse();
        response.setId(entity.getId());
        response.setFirstName(entity.getFirstName());
        response.setLastName(entity.getLastName());
        response.setNickname(entity.getNickname());
        response.setEmail(entity.getEmail());
        response.setRole(entity.getRole());
        response.setArchived(entity.isArchived());

        return response;
    }

    public List<UserResponse> entitiesToListResponse(Collection<User> entityList) {
        return entityList.stream().map(this::entityToResponse).toList();
    }
}
