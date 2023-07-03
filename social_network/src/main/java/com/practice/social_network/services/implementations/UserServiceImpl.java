package com.practice.social_network.services.implementations;

import com.practice.social_network.dtos.UserDTO;
import com.practice.social_network.entities.User;
import com.practice.social_network.repositories.UserRepository;
import com.practice.social_network.services.intefaces.mappers.DTOMapper;
import com.practice.social_network.services.intefaces.UserService;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    private PasswordEncoder passEncoder;

    private DTOMapper dtoMapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passEncoder) {
        this.repository = repository;
        this.passEncoder = passEncoder;
        this.dtoMapper = Mappers.getMapper(DTOMapper.class);
    }

    @Override
    public UserDTO createUser(UserDTO user) throws DataIntegrityViolationException {
        user.setPassword(passEncoder.encode(user.getPassword()));
        return dtoMapper.userToDto(repository.save(dtoMapper.dtoToUser(user)));
    }

    @Override
    public UserDTO updateUser(UserDTO user) throws DataIntegrityViolationException, IllegalArgumentException {
        if (!repository.existsById(user.getId())) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        User userEntity = repository.findById(user.getId()).get();
        dtoMapper.updateUserFromDto(user, userEntity);
        return dtoMapper.userToDto(repository.save(userEntity));
    }

    @Override
    public UserDTO deleteUser(int userId) throws IllegalArgumentException {
        if (!repository.existsById(userId)) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        return dtoMapper.userToDto(repository.deleteById(userId));
    }

    @Override
    public UserDTO followToUser(int userId, int userToFollowId) throws IllegalArgumentException {
        Optional<User> followingUser = repository.findById(userId);
        Optional<User> userToFollow = repository.findById(userToFollowId);
        if (followingUser.isEmpty() || userToFollow.isEmpty()) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        followingUser.get().addFollowing(userToFollow.get());
        return dtoMapper.userToDto(repository.save(followingUser.get()));
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        repository.findAll().forEach(resultList::add);
        return resultList.stream().map(user -> dtoMapper.userToDto(user)).toList();
    }

    @Override
    public UserDTO changeUserPassword(int userId, String newPassword) {
        if(!repository.existsById(userId)){
            throw new IllegalArgumentException("Wrong user ID");
        }
        repository.updatePassword(userId, passEncoder.encode(newPassword));
        return dtoMapper.userToDto(repository.findById(userId).get());
    }
}
