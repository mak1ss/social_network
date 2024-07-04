package com.practice.social_network.mappers;

import com.practice.social_network.dtos.postComment.PostCommentRequest;
import com.practice.social_network.dtos.postComment.PostCommentResponse;
import com.practice.social_network.entities.PostComment;
import com.practice.social_network.repositories.PostRepository;
import com.practice.social_network.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class PostCommentMapper implements Mapper<PostComment, PostCommentResponse, PostCommentRequest> {

    private PostRepository postRepository;
    private UserRepository userRepository;
    private UserMapper userMapper;

    public PostComment requestToEntity(PostCommentRequest request) {
        PostComment entity = new PostComment();
        entity.setId(request.getId());
        entity.setPost(postRepository.findById(request.getPostId()).orElseThrow());
        entity.setUser(userRepository.findById(request.getUserId()).orElseThrow());
        entity.setCommentBody(request.getCommentBody());

        return entity;
    }

    @Override
    public PostCommentResponse entityToResponse(PostComment entity) {
        PostCommentResponse response = new PostCommentResponse();
        response.setId(entity.getId());
        response.setCommentBody(entity.getCommentBody());
        response.setUser(userMapper.entityToResponse(entity.getUser()));

        return response;
    }

    public List<PostCommentResponse> entitiesToListResponse(Collection<PostComment> entityList) {
        return entityList.stream().map(this::entityToResponse).toList();
    }
}
