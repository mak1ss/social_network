package com.practice.social_network.services;

import com.practice.social_network.daos.AbstractDao;
import com.practice.social_network.daos.PostDao;
import com.practice.social_network.model.Post;
import com.practice.social_network.repositories.PostRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostService extends AbstractService<Post> {

    private final PostDao dao;
    private final PostRepository repo;
    @Override
    protected AbstractDao<Post> getDao() {
        return dao;
    }

    public List<Post> getUserPosts(Integer userId) {
        return repo.getPostsByUserId(userId);
    }
}
