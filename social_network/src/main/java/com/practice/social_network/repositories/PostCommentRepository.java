package com.practice.social_network.repositories;

import com.practice.social_network.entities.PostComment;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostCommentRepository extends PagingAndSortingRepository<PostComment, Integer>, JpaRepository<PostComment, Integer> {

    Optional<List<PostComment>> findByPostId(int postId, Pageable pageable);
}
