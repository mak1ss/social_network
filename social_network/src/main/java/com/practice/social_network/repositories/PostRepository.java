package com.practice.social_network.repositories;

import com.practice.social_network.entities.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Integer>, JpaRepository<Post, Integer> {

    List<Post> getPostsByUserId(Integer userId);

}
