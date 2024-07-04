package com.practice.social_network.mappers;

import com.practice.social_network.dtos.post.PostRequest;
import com.practice.social_network.dtos.post.PostResponse;
import com.practice.social_network.entities.Post;
import com.practice.social_network.repositories.PostRepository;
import com.practice.social_network.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class PostMapper implements Mapper<Post, PostResponse, PostRequest> {

    private UserRepository userRepository;
    private PostRepository postRepository;
    private UserMapper mapper;
    private PostCommentMapper postCommentMapper;

    public Post requestToEntity(PostRequest request) {
        Post entity = new Post();
        entity.setId(request.getId());
        entity.setUser(userRepository.findById(request.getUserId()).orElseThrow());

        entity.setPostBody(request.getPostBody());

        if(entity.getId() != null){
            Post actualEntity = postRepository.findById(entity.getId()).orElseThrow();
            entity.setCreationDate(actualEntity.getCreationDate());
            entity.setComments(actualEntity.getComments());
            entity.setLikes(actualEntity.getLikes());
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
        response.setComments(postCommentMapper.entitiesToListResponse(entity.getComments()));
        response.setLikes(entity.getLikes().size());

        return response;
    }

    public List<PostResponse> entitiesToListResponse(Collection<Post> entityList) {
        return entityList.stream().map(this::entityToResponse).toList();
    }
}
