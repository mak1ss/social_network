package com.practice.social_network.repositories;

import com.practice.social_network.entities.PostComment;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PostCommentRepository extends BaseCRUDRepository<PostComment> {

    Optional<List<PostComment>> findByPostId(int postId, Pageable pageable);
}
