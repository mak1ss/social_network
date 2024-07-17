package com.practice.social_network.services;

import com.practice.social_network.daos.AbstractDao;
import com.practice.social_network.dtos.userFollow.UserFollowResponse;
import com.practice.social_network.model.UserFollow;
import com.practice.social_network.repositories.UserFollowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserFollowService extends AbstractService<UserFollow> {

    private final AbstractDao<UserFollow> dao;
    private final UserFollowRepository repo;

    @Override
    protected AbstractDao<UserFollow> getDao() {
        return dao;
    }

    public List<UserFollow> getUserFollows(Integer userId) {
        return repo.findByFollowerId(userId);
    }

    public List<UserFollow> getUserFollowers(Integer userId) {
        return repo.findByFollowedId(userId);
    }


}
