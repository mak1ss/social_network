package com.practice.social_network.services.implementations;

import com.practice.social_network.dtos.post.PostResponse;
import com.practice.social_network.dtos.post.PostRequest;
import com.practice.social_network.entities.Post;
import com.practice.social_network.entities.User;

import com.practice.social_network.mappers.PostMapper;
import com.practice.social_network.repositories.UserRepository;
import com.practice.social_network.services.intefaces.PostService;
import com.practice.social_network.repositories.PostRepository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private UserRepository userRepository;
    private PostRepository postRepository;

    private PostMapper postMapper;

    @Override
    public PostResponse createPost(PostRequest post) throws IllegalArgumentException {
        Optional<User> userById = userRepository.findById(post.getUserId());
        if (userById.isEmpty()) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        Post postEntity = postMapper.requestToEntity(post);
        postEntity.setUser(userById.get());
        return postMapper.entityToResponse(postRepository.save(postEntity));
    }

    @Override
    public PostResponse updatePost(PostRequest post) throws IllegalArgumentException {
        Post entity = postMapper.requestToEntity(post);
        entity = postRepository.save(entity);
        return postMapper.entityToResponse(entity);
    }

    @Override
    public void deletePost(Integer postId) throws IllegalArgumentException {
        if(!postRepository.existsById(postId)) {
            throw new IllegalArgumentException("Wrong post ID");
        }
        postRepository.deleteById(postId);
    }

    @Override
    public List<PostResponse> getUserPosts(Integer userId) throws IllegalArgumentException {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        return postMapper.entitiesToListResponse(postRepository.getPostsByUserId(userId));
    }
}
