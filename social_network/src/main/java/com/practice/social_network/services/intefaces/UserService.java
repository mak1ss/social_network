package com.practice.social_network.services.intefaces;

import com.practice.social_network.entities.User;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface UserService {

    User createUser(User user) throws DataIntegrityViolationException;

    User updateUser(User user) throws DataIntegrityViolationException, IllegalArgumentException;

    User deleteUser(int userId) throws IllegalArgumentException;

    User followToUser(int userId, int userToFollowId) throws IllegalArgumentException;

    List<User> getAllUsers();
}
