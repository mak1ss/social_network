package com.practice.social_network.services;

import com.practice.social_network.daos.AbstractDao;
import com.practice.social_network.daos.PostCommentDao;
import com.practice.social_network.model.PostComment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PostCommentService extends AbstractService<PostComment> {

    private final PostCommentDao dao;

    @Override
    protected AbstractDao<PostComment> getDao() {
        return dao;
    }

}
