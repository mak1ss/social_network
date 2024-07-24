package com.network.services;

import com.network.daos.AbstractDao;
import com.network.daos.PostLikeDao;
import com.network.model.PostLike;
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
