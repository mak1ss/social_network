package com.practice.social_network.services.intefaces;

import com.practice.social_network.dtos.user.UserRequest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface UserService {

    UserRequest createUser(UserRequest user) throws DataIntegrityViolationException;

    UserRequest updateUser(UserRequest user) throws DataIntegrityViolationException, IllegalArgumentException;

    UserRequest deleteUser(int userId) throws IllegalArgumentException;

    UserRequest followToUser(int userId, int userToFollowId) throws IllegalArgumentException;

    List<UserRequest> getAllUsers();

    UserRequest changeUserPassword(int userId, String newPassword);
}
