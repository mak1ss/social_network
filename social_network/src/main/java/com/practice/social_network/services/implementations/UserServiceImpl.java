package com.practice.social_network.services.implementations;

import com.practice.social_network.entities.User;
import com.practice.social_network.repositories.UserRepository;
import com.practice.social_network.services.intefaces.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User createUser(User user) throws DataIntegrityViolationException {
        return repository.save(user);
    }

    @Override
    public User updateUser(User user) throws DataIntegrityViolationException, IllegalArgumentException {
        if (!repository.existsById(user.getId())) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        return repository.save(user);
    }

    @Override
    public User deleteUser(int userId) throws IllegalArgumentException {
        if (!repository.existsById(userId)) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        return repository.deleteById(userId);
    }

    @Override
    public User followToUser(int userId, int userToFollowId) throws IllegalArgumentException {
        Optional<User> followingUser = repository.findById(userId);
        Optional<User> userToFollow = repository.findById(userToFollowId);
        if (followingUser.isEmpty() || userToFollow.isEmpty()) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        followingUser.get().addFollowing(userToFollow.get());
        return repository.save(followingUser.get());
    }

    @Override
    public List<User> getAllUsers() {
        List<User> resultList = new ArrayList<>();
        repository.findAll().forEach(resultList::add);
        return resultList;
    }
}
