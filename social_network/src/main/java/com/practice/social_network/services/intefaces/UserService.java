package com.practice.social_network.services.intefaces;

import com.practice.social_network.dtos.UserDTO;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO user) throws DataIntegrityViolationException;

    UserDTO updateUser(UserDTO user) throws DataIntegrityViolationException, IllegalArgumentException;

    UserDTO deleteUser(int userId) throws IllegalArgumentException;

    UserDTO followToUser(int userId, int userToFollowId) throws IllegalArgumentException;

    List<UserDTO> getAllUsers();

    UserDTO changeUserPassword(int userId, String newPassword);
}
