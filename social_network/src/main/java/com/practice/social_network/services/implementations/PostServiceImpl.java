package com.practice.social_network.services.implementations;

import com.practice.social_network.dtos.PostDTO;
import com.practice.social_network.entities.Post;
import com.practice.social_network.entities.User;

import com.practice.social_network.repositories.UserRepository;
import com.practice.social_network.services.intefaces.PostService;
import com.practice.social_network.repositories.PostRepository;
import com.practice.social_network.services.intefaces.mappers.DTOMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private DTOMapper dtoMapper;
    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.dtoMapper = Mappers.getMapper(DTOMapper.class);
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO post, int userId) throws IllegalArgumentException {
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isEmpty()) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        Post postEntity = dtoMapper.postDtoToPost(post);
        postEntity.setUser(userById.get(), false);
        return dtoMapper.postToPostDto(postRepository.save(postEntity));
    }

    @Override
    public PostDTO updatePost(PostDTO post, int userId) throws IllegalArgumentException {
        boolean isUserEmpty = userRepository.existsById(userId);
        boolean isPostEmpty = postRepository.existsById(post.getId());
        if (!(isUserEmpty || isPostEmpty)) {
            throw new IllegalArgumentException("Wrong user ID or post ID");
        }
        postRepository.updatePost(post.getPostBody(), post.getId(), userId);
        return dtoMapper.postToPostDto(postRepository.findById(post.getId()).get());
    }

    @Override
    public PostDTO deletePost(int postId, int userId) throws IllegalArgumentException {
        boolean isUserEmpty = userRepository.existsById(userId);
        boolean isPostEmpty = postRepository.existsById(postId);
        if (!(isUserEmpty || isPostEmpty)) {
            throw new IllegalArgumentException("Wrong user ID or post ID");
        }
        return dtoMapper.postToPostDto(postRepository.deleteById(postId));
    }

    @Override
    public List<PostDTO> getUserPosts(int userId) throws IllegalArgumentException {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        return postRepository.getPostsByUserId(userId).stream().map(post -> dtoMapper.postToPostDto(post)).toList();
    }

    @Override
    public List<PostDTO> getFriendsPosts(int userId, int pageNumber) throws IllegalArgumentException {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        return postRepository.getFriendsPosts(userId, PageRequest.of(pageNumber, 20))
                .stream().map(post -> dtoMapper.postToPostDto(post)).toList();
    }
}
