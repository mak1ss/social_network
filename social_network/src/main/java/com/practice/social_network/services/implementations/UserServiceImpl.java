package com.practice.social_network.services.implementations;

import com.practice.social_network.dtos.user.ChangePasswordRequest;
import com.practice.social_network.dtos.user.UserRequest;
import com.practice.social_network.dtos.user.UserResponse;
import com.practice.social_network.entities.User;
import com.practice.social_network.mappers.UserMapper;
import com.practice.social_network.repositories.UserRepository;
import com.practice.social_network.services.intefaces.UserService;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    private PasswordEncoder passEncoder;

    private UserMapper userMapper;

    @Override
    public UserResponse createUser(UserRequest user) throws DataIntegrityViolationException {
        user.setPassword(passEncoder.encode(user.getPassword()));
        return userMapper.entityToResponse(repository.save(userMapper.requestToEntity(user)));
    }

    @Override
    public UserResponse updateUser(UserRequest user) throws DataIntegrityViolationException, IllegalArgumentException {
        if (!repository.existsById(user.getId())) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        User userEntity = userMapper.requestToEntity(user);
        return userMapper.entityToResponse(repository.save(userEntity));
    }

    @Override
    public void deleteUser(Integer userId) throws IllegalArgumentException {
        if (!repository.existsById(userId)) {
            throw new IllegalArgumentException("Wrong user ID");
        }

        repository.deleteById(userId);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> resultList = new ArrayList<>(repository.findAll());
        return resultList.stream().map(user -> userMapper.entityToResponse(user)).toList();
    }

    @Override
    public UserResponse changeUserPassword(Integer userId, ChangePasswordRequest changeRequest) {
        User entity = repository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("Wrong user ID")
        );

        if (isPasswordsMatch(changeRequest.getOldPassword(), entity.getPassword())) {
            entity.setPassword(passEncoder.encode(changeRequest.getNewPassword()));
            entity = repository.save(entity);
            return userMapper.entityToResponse(entity);
        }

        throw new IllegalArgumentException("Wrong password");
    }

    public boolean isPasswordsMatch(String oldPassword, String newPassword) {
        return passEncoder.matches(oldPassword, newPassword);
    }
}
