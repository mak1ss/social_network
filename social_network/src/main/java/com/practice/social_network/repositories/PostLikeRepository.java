package com.practice.social_network.repositories;

import com.practice.social_network.entities.PostLike;

import java.util.List;

public interface PostLikeRepository extends  BaseCRUDRepository<PostLike> {

    List<PostLike> findByPostId(Integer postId);
}
