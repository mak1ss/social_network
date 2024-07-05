package com.practice.social_network.services.implementations;

import com.practice.social_network.dtos.post.PostResponse;
import com.practice.social_network.dtos.post.PostRequest;
import com.practice.social_network.entities.Post;
import com.practice.social_network.entities.User;

import com.practice.social_network.mappers.PostCommentMapper;
import com.practice.social_network.mappers.PostMapper;
import com.practice.social_network.repositories.PostCommentRepository;
import com.practice.social_network.repositories.UserRepository;
import com.practice.social_network.services.intefaces.PostService;
import com.practice.social_network.repositories.PostRepository;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private UserRepository userRepository;
    private PostRepository postRepository;
    private PostCommentRepository commentRepository;

    private PostMapper postMapper;
    private PostCommentMapper commentMapper;

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
        boolean isUserEmpty = !userRepository.existsById(post.getUserId());
        boolean isPostEmpty = !postRepository.existsById(post.getId());
        if (isUserEmpty || isPostEmpty) {
            throw new IllegalArgumentException("Wrong user ID or post ID");
        }
        postRepository.updatePost(post.getPostBody(), post.getId(), post.getUserId());

        return postMapper.entityToResponse(postRepository.findById(post.getId()).get());
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
        return postRepository.getPostsByUserId(userId).stream().map(postMapper::entityToResponse).toList();
    }

    @Override
    public List<PostResponse> getFriendsPosts(Integer userId, Integer pageNumber) throws IllegalArgumentException {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        List<Post> list = postRepository.getFriendsPosts(userId, PageRequest.of(pageNumber, 10));
        return list
                .stream().map(postMapper::entityToResponse).toList();
    }

    @Override
    public PostResponse likePost(Integer userId, Integer postId) throws IllegalArgumentException {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Post> postToLike = postRepository.findById(postId);

        if (userOptional.isEmpty() || postToLike.isEmpty()) {
            throw new IllegalArgumentException("Wrong user ID or post ID");
        }

        User user = userOptional.get();
        Post post = postToLike.get();
        if (!post.getLikes().contains(user)) {
            post.getLikes().add(user);
        } else {
            post.getLikes().remove(user);
        }

        postRepository.save(post);

        return postMapper.entityToResponse(postRepository.findById(postId).get());
    }

}
