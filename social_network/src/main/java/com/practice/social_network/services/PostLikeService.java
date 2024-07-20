package com.practice.social_network.services;

import com.practice.social_network.daos.AbstractDao;
import com.practice.social_network.daos.PostLikeDao;
import com.practice.social_network.model.PostLike;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostLikeService extends AbstractService<PostLike>  {

    private final PostLikeDao dao;

    @Override
    protected AbstractDao<PostLike> getDao() {
        return dao;
    }

}
