package com.practice.social_network.services.implementations;

import com.practice.social_network.dtos.postLike.PostLikeRequest;
import com.practice.social_network.dtos.postLike.PostLikeResponse;
import com.practice.social_network.entities.PostLike;
import com.practice.social_network.mappers.PostLikeMapper;
import com.practice.social_network.repositories.PostLikeRepository;
import com.practice.social_network.repositories.PostRepository;
import com.practice.social_network.services.intefaces.PostLikeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PostLikeServiceImpl implements PostLikeService {

    private PostRepository postRepository;
    private PostLikeRepository postLikeRepository;
    private PostLikeMapper postLikeMapper;



    @Override
    public PostLikeResponse createPostLike(PostLikeRequest request) {
        PostLike entity = postLikeMapper.requestToEntity(request);

        entity = postLikeRepository.save(entity);

        return postLikeMapper.entityToResponse(entity);
    }

    @Override
    public List<PostLikeResponse> getPostLikes(Integer postId) {
        if(!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("Post not found");
        }

        return postLikeMapper.entitiesToListResponse(postLikeRepository.findByPostId(postId));
    }

    @Override
    public void deletePostLike(PostLikeRequest request) {
        PostLike entity = postLikeMapper.requestToEntity(request);

        postLikeRepository.delete(entity);
    }
}
