package com.practice.social_network.daos;

import com.practice.social_network.model.Post;
import com.practice.social_network.repositories.PostRepository;
import com.practice.social_network.repositories.PrimaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostDao extends AbstractDao<Post> {

    private final PostRepository repository;

    @Override
    protected PrimaryRepository<Integer, Post> getRepository() {
        return repository;
    }
}
