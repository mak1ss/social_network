package com.network.services;

import com.network.daos.AbstractDao;
import com.network.daos.PostDao;
import com.network.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostService extends AbstractService<Post> {

    private final PostDao dao;

    @Override
    protected AbstractDao<Post> getDao() {
        return dao;
    }

}
