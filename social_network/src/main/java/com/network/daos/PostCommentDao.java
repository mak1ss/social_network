package com.network.daos;

import com.network.model.PostComment;
import com.network.repositories.PrimaryRepository;
import com.network.repositories.PostCommentRepository;
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
