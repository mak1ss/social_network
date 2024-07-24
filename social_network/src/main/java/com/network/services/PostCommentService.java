package com.network.services;

import com.network.daos.AbstractDao;
import com.network.daos.PostCommentDao;
import com.network.model.PostComment;
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
