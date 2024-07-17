package com.practice.social_network.services;

import com.practice.social_network.daos.AbstractDao;
import com.practice.social_network.daos.PostCommentDao;
import com.practice.social_network.model.PostComment;
import com.practice.social_network.repositories.PostCommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostCommentService extends AbstractService<PostComment> {

    private final PostCommentDao dao;
    private final PostCommentRepository repository;

    @Override
    protected AbstractDao<PostComment> getDao() {
        return dao;
    }

    public List<PostComment> getPostComments(Integer postId, Integer pageNumber) {
        return repository
                .findByPostId(postId, PageRequest.of(pageNumber, 5))
                .orElseThrow();
    }
}
