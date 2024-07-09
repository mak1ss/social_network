package com.practice.social_network.repositories;

import com.practice.social_network.entities.PostLike;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Integer> {

    List<PostLike> findByPostId(Integer postId);
}
