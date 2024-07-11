package com.practice.social_network.daos;

import com.practice.social_network.model.PostComment;
import com.practice.social_network.repositories.PostCommentRepository;
import com.practice.social_network.repositories.PrimaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostCommentDao extends AbstractDao<PostComment> {

    private final PostCommentRepository repository;

    @Override
    protected PrimaryRepository<Integer, PostComment> getRepository() {
        return repository;
    }
}
