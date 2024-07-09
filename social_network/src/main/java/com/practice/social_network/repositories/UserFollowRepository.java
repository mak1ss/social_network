package com.practice.social_network.repositories;

import com.practice.social_network.entities.UserFollow;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface UserFollowRepository extends JpaRepository<UserFollow, Integer> {

    List<UserFollow> findByFollowerId(Integer followerId);

    List<UserFollow> findByFollowedId(Integer followedId);
}
