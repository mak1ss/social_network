package com.practice.social_network.repositories;

import com.practice.social_network.entities.PostLike;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
}
