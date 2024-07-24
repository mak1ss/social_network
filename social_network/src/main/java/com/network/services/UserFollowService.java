package com.network.services;

import com.network.daos.AbstractDao;
import com.network.model.UserFollow;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserFollowService extends AbstractService<UserFollow> {

    private final AbstractDao<UserFollow> dao;

    @Override
    protected AbstractDao<UserFollow> getDao() {
        return dao;
    }

}
