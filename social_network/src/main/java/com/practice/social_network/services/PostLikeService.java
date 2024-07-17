package com.practice.social_network.services;

import com.practice.social_network.daos.AbstractDao;
import com.practice.social_network.daos.PostLikeDao;
import com.practice.social_network.model.PostLike;
import com.practice.social_network.repositories.PostLikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostLikeService extends AbstractService<PostLike>  {

    private final PostLikeDao dao;
    private final PostLikeRepository repo;

    @Override
    protected AbstractDao<PostLike> getDao() {
        return dao;
    }

    public List<PostLike> getPostLikes(Integer postId) {
        return repo.findByPostId(postId);
    }
}
