package com.practice.social_network.mappers;

import com.practice.social_network.dtos.post.PostRequest;
import com.practice.social_network.dtos.post.PostResponse;
import com.practice.social_network.model.Post;
import com.practice.social_network.repositories.PostRepository;
import com.practice.social_network.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostMapper implements Mapper<Post, PostResponse, PostRequest> {

    private UserRepository userRepository;
    private PostRepository postRepository;
    private UserMapper mapper;

    public Post requestToEntity(PostRequest request, Optional<Integer> id) {
        Post entity = new Post();
        entity.setId(id.orElse(null));
        entity.setUser(userRepository.findById(request.getUserId()).orElseThrow());

        entity.setPostBody(request.getPostBody());

        if(id.isPresent()){
            Post actualEntity = postRepository.findById(id.get()).orElseThrow();
            entity.setCreationDate(actualEntity.getCreationDate());
            entity.setComments(actualEntity.getComments());
            entity.setPostLikes(actualEntity.getPostLikes());
        } else {
            entity.setCreationDate(LocalDateTime.now());
        }

        return entity;
    }

    public PostResponse entityToResponse(Post entity) {
        PostResponse response = new PostResponse();
        response.setId(entity.getId());
        response.setUser(mapper.entityToResponse(entity.getUser()));
        response.setCreationDate(entity.getCreationDate());
        response.setPostBody(entity.getPostBody());
        response.setComments(entity.getComments().size());
        response.setLikes(entity.getPostLikes().size());

        return response;
    }

    public List<PostResponse> entitiesToListResponse(Collection<Post> entityList) {
        return entityList.stream().map(this::entityToResponse).toList();
    }
}
