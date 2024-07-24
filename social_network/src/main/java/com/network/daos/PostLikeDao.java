package com.network.daos;

import com.network.model.PostLike;
import com.network.repositories.PrimaryRepository;
import com.network.repositories.PostLikeRepository;
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
