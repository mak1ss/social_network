package com.practice.social_network.mappers;

import com.practice.social_network.dtos.postLike.PostLikeRequest;
import com.practice.social_network.dtos.postLike.PostLikeResponse;
import com.practice.social_network.entities.PostLike;
import com.practice.social_network.repositories.PostRepository;
import com.practice.social_network.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class PostLikeMapper implements Mapper<PostLike, PostLikeResponse, PostLikeRequest> {

    private UserMapper userMapper;
    private PostRepository postRepository;
    private UserRepository userRepository;

    @Override
    public PostLike requestToEntity(PostLikeRequest request) {
        PostLike entity = new PostLike();
        entity.setId(request.getId());
        entity.setPost(postRepository.findById(request.getPostId()).orElseThrow(
                () -> new IllegalArgumentException("Post not found")
        ));
        entity.setUser(userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("User not found")
        ));
        entity.setLikedAt(LocalDateTime.now());

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
