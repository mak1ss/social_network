package com.practice.social_network.repositories;

import com.practice.social_network.entities.Post;

import java.util.List;

public interface PostRepository extends BaseCRUDRepository<Post> {

    List<Post> getPostsByUserId(Integer userId);

}
