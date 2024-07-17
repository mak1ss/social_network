package com.practice.social_network.daos;

import com.practice.social_network.model.User;
import com.practice.social_network.repositories.PrimaryRepository;
import com.practice.social_network.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDao extends AbstractDao<User> {

    private final UserRepository repository;

    @Override
    protected PrimaryRepository<Integer, User> getRepository() {
        return repository;
    }
}
