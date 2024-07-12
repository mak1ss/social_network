package com.practice.social_network.mappers;

import com.practice.social_network.dtos.postComment.PostCommentRequest;
import com.practice.social_network.dtos.postComment.PostCommentResponse;
import com.practice.social_network.model.PostComment;
import com.practice.social_network.services.PostCommentService;
import com.practice.social_network.services.PostService;
import com.practice.social_network.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostCommentMapper implements Mapper<PostComment, PostCommentResponse, PostCommentRequest> {

    private PostCommentService postCommentService;
    private PostService postService;
    private UserService userService;
    private UserMapper userMapper;

    public PostComment requestToEntity(PostCommentRequest request, Optional<Integer> id) {
        PostComment entity = new PostComment();
        entity.setId(id.orElse(null));
        entity.setCommentBody(request.getCommentBody());

        if(id.isPresent()) {
            PostComment actualEntity = postCommentService.getById(id.get()).orElseThrow();
            entity.setCreationDate(actualEntity.getCreationDate());
            entity.setPost(actualEntity.getPost());
            entity.setUser(actualEntity.getUser());
        } else {
            entity.setCreationDate(LocalDateTime.now());
            entity.setPost(postService.getById(request.getPostId()).orElseThrow());
            entity.setUser(userService.getById(request.getUserId()).orElseThrow());
        }

        entity.setArchived(false);

        return entity;
    }

    @Override
    public PostCommentResponse entityToResponse(PostComment entity) {
        PostCommentResponse response = new PostCommentResponse();
        response.setId(entity.getId());
        response.setCommentBody(entity.getCommentBody());
        response.setUser(userMapper.entityToResponse(entity.getUser()));
        response.setCreationDate(entity.getCreationDate());
        response.setArchived(entity.isArchived());

        return response;
    }

    public List<PostCommentResponse> entitiesToListResponse(Collection<PostComment> entityList) {
        return entityList.stream().map(this::entityToResponse).toList();
    }
}
