package com.practice.social_network.repositories;

import com.practice.social_network.entities.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Integer> {

    List<Follow> findByFollowerId(Integer followerId);

    List<Follow> findByFollowedId(Integer followedId);
}
