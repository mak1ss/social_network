package com.practice.social_network.repositories;

import com.practice.social_network.entities.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    Optional<List<Post>> findByUserId(int userId);

}
