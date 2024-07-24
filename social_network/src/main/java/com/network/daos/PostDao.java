package com.network.daos;

import com.network.model.Post;
import com.network.repositories.PrimaryRepository;
import com.network.repositories.PostRepository;
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
