package com.network.daos;

import com.network.model.UserFollow;
import com.network.repositories.PrimaryRepository;
import com.network.repositories.UserFollowRepository;
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
