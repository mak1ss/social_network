package com.practice.social_network.mappers;

import com.practice.social_network.dtos.postLike.PostLikeRequest;
import com.practice.social_network.dtos.postLike.PostLikeResponse;
import com.practice.social_network.model.PostLike;
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
public class PostLikeMapper implements Mapper<PostLike, PostLikeResponse, PostLikeRequest> {

    private UserMapper userMapper;
    private PostService postService;
    private UserService userService;

    @Override
    public PostLike requestToEntity(PostLikeRequest request, Optional<Integer> id) {
        PostLike entity = new PostLike();

        entity.setPost(postService.getById(request.getPostId()).orElseThrow());
        entity.setUser(userService.getById(request.getUserId()).orElseThrow());

        if(entity.getPost().getPostLikes().contains(entity)) {
            PostLike actualEntity = entity.getPost().getPostLikes().stream()
                    .filter(actual -> actual.equals(entity))
                    .findFirst().get();

            entity.setId(actualEntity.getId());
            entity.setLikedAt(actualEntity.getLikedAt());
        } else {
            entity.setLikedAt(LocalDateTime.now());
        }

        return entity;
    }

    @Override
    public PostLikeResponse entityToResponse(PostLike entity) {
        PostLikeResponse response = new PostLikeResponse();
        response.setId(entity.getId());
        response.setUser(userMapper.entityToResponse(entity.getUser()));
        response.setLikedAt(entity.getLikedAt());

        return response;
    }

    @Override
    public List<PostLikeResponse> entitiesToListResponse(Collection<PostLike> entityList) {
        return entityList.stream().map(this::entityToResponse).toList();
    }
}
