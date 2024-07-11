package com.practice.social_network.daos;

import com.practice.social_network.model.PostLike;
import com.practice.social_network.repositories.PostLikeRepository;
import com.practice.social_network.repositories.PrimaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostLikeDao extends AbstractDao<PostLike> {

    private final PostLikeRepository repository;

    @Override
    protected PrimaryRepository<Integer, PostLike> getRepository() {
        return repository;
    }
}
