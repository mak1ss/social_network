package com.practice.social_network.repositories;

import com.practice.social_network.entities.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

    Optional<List<Post>> findByUserId(int userId);

    Post save(Post post);

    Post deleteById(int postId);

    Optional<Post> findById(int postId);

    List<Post> getPostsByUserId(int userId);

    @Query("""
            SELECT p
            FROM Post p
            WHERE p.user IN
                (SELECT f
                FROM User u
                JOIN u.followings f
                WHERE u.id = :userId)
            """)
    List<Post> getFriendsPosts(int userId, Pageable pageable);

    boolean existsById(int postId);

    @Modifying
    @Query("""
           UPDATE Post post
           SET post.postBody = ?1
           WHERE post.id = ?2
           AND post.user.id = ?3
           """)
    void updatePost(String postBody, int postId, int userId);

}
