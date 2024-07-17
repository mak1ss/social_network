package com.practice.social_network.daos;

import com.practice.social_network.model.UserFollow;
import com.practice.social_network.repositories.PrimaryRepository;
import com.practice.social_network.repositories.UserFollowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserFollowDao extends AbstractDao<UserFollow> {

    private final UserFollowRepository repository;

    @Override
    protected PrimaryRepository<Integer, UserFollow> getRepository() {
        return repository;
    }
}
