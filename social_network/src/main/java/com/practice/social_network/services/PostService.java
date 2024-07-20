package com.practice.social_network.services;

import com.practice.social_network.daos.AbstractDao;
import com.practice.social_network.daos.PostDao;
import com.practice.social_network.model.Post;
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
