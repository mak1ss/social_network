package com.practice.social_network.services.intefaces;

import com.practice.social_network.dtos.user.ChangePasswordRequest;
import com.practice.social_network.dtos.user.UserRequest;
import com.practice.social_network.dtos.user.UserResponse;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest user) throws DataIntegrityViolationException;

    UserResponse updateUser(UserRequest user) throws DataIntegrityViolationException, IllegalArgumentException;

    void deleteUser(Integer userId) throws IllegalArgumentException;

    UserResponse followToUser(Integer userId, Integer userToFollowId) throws IllegalArgumentException;

    List<UserResponse> getAllUsers();

    UserResponse changeUserPassword(Integer userId, ChangePasswordRequest changePasswordRequest);
}
