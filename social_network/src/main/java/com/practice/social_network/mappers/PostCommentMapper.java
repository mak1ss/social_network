package com.practice.social_network.mappers;

import com.practice.social_network.dtos.postComment.PostCommentRequest;
import com.practice.social_network.dtos.postComment.PostCommentResponse;
import com.practice.social_network.entities.PostComment;
import com.practice.social_network.repositories.PostCommentRepository;
import com.practice.social_network.repositories.PostRepository;
import com.practice.social_network.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class PostCommentMapper implements Mapper<PostComment, PostCommentResponse, PostCommentRequest> {

    private PostCommentRepository postCommentRepository;
    private PostRepository postRepository;
    private UserRepository userRepository;
    private UserMapper userMapper;

    public PostComment requestToEntity(PostCommentRequest request) {
        PostComment entity = new PostComment();
        entity.setId(request.getId());
        entity.setCommentBody(request.getCommentBody());

        if(entity.getId() != null) {
            PostComment actualEntity = postCommentRepository.findById(entity.getId()).orElseThrow();
            entity.setCreationDate(actualEntity.getCreationDate());
            entity.setPost(actualEntity.getPost());
            entity.setUser(actualEntity.getUser());
        } else {
            entity.setCreationDate(LocalDateTime.now());
            entity.setPost(postRepository.findById(request.getPostId()).orElseThrow());
            entity.setUser(userRepository.findById(request.getUserId()).orElseThrow());
        }

        return entity;
    }

    @Override
    public PostCommentResponse entityToResponse(PostComment entity) {
        PostCommentResponse response = new PostCommentResponse();
        response.setId(entity.getId());
        response.setCommentBody(entity.getCommentBody());
        response.setUser(userMapper.entityToResponse(entity.getUser()));
        response.setCreationDate(entity.getCreationDate());

        return response;
    }

    public List<PostCommentResponse> entitiesToListResponse(Collection<PostComment> entityList) {
        return entityList.stream().map(this::entityToResponse).toList();
    }
}
