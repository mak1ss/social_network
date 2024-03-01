package com.practice.social_network.services.implementations;

import com.practice.social_network.dtos.PostCommentDTO;
import com.practice.social_network.dtos.PostDTO;
import com.practice.social_network.entities.Post;
import com.practice.social_network.entities.PostComment;
import com.practice.social_network.entities.User;

import com.practice.social_network.repositories.CommentRepository;
import com.practice.social_network.repositories.UserRepository;
import com.practice.social_network.services.intefaces.PostService;
import com.practice.social_network.repositories.PostRepository;
import com.practice.social_network.services.intefaces.mappers.PostDTOMapper;

import org.mapstruct.factory.Mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private PostDTOMapper dtoMapper;
    private UserRepository userRepository;
    private PostRepository postRepository;

    private CommentRepository commentRepository;

    @Autowired
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository) {
        this.dtoMapper = Mappers.getMapper(PostDTOMapper.class);
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public PostDTO createPost(PostDTO post, int userId) throws IllegalArgumentException {
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isEmpty()) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        Post postEntity = dtoMapper.dtoToPost(post);
        postEntity.setUser(userById.get(), false);
        return dtoMapper.postToDto(postRepository.save(postEntity));
    }

    @Override
    public PostDTO updatePost(PostDTO post, int userId) throws IllegalArgumentException {
        boolean isUserEmpty = !userRepository.existsById(userId);
        boolean isPostEmpty = !postRepository.existsById(post.getId());
        if (isUserEmpty || isPostEmpty) {
            throw new IllegalArgumentException("Wrong user ID or post ID");
        }
        postRepository.updatePost(post.getPostBody(), post.getId(), userId);

        return dtoMapper.postToDto(postRepository.findById(post.getId()).get());
    }

    @Override
    public PostDTO deletePost(int postId, int userId) throws IllegalArgumentException {
        boolean isUserEmpty = !userRepository.existsById(userId);
        boolean isPostEmpty = !postRepository.existsById(postId);
        if (isUserEmpty || isPostEmpty) {
            throw new IllegalArgumentException("Wrong user ID or post ID");
        }
        return dtoMapper.postToDto(postRepository.deleteById(postId));
    }

    @Override
    public List<PostDTO> getUserPosts(int userId) throws IllegalArgumentException {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        return postRepository.getPostsByUserId(userId).stream().map(dtoMapper::postToDto).toList();
    }

    @Override
    public List<PostDTO> getFriendsPosts(int userId, int pageNumber) throws IllegalArgumentException {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        List<Post> list = postRepository.getFriendsPosts(userId, PageRequest.of(pageNumber, 10));
        return list
                .stream().map(dtoMapper::postToDto).toList();
    }

    @Override
    public PostDTO likePost(int userId, int postId) throws IllegalArgumentException {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Post> postToLike = postRepository.findById(postId);

        if (userOptional.isEmpty() || postToLike.isEmpty()) {
            throw new IllegalArgumentException("Wrong user ID or post ID");
        }

        User user = userOptional.get();

        if (!user.getLikedPosts().contains(postToLike.get())) {
            user.getLikedPosts().add(postToLike.get());
        } else {
            user.getLikedPosts().remove(postToLike.get());
        }

        userRepository.save(user);

        return dtoMapper.postToDto(postRepository.findById(postId).get());
    }

    @Override
    public PostDTO leaveComment(String comment, int postId, int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        Optional<Post> postOptional = postRepository.findById(postId);

        if (userOptional.isEmpty() || postOptional.isEmpty()) {
            throw new IllegalArgumentException("Wrong user ID or post ID");
        }

        Post post = postOptional.get();

        PostComment postComment = new PostComment();
        postComment.setPost(post);
        postComment.setUser(userOptional.get());
        postComment.setCommentBody(comment);

        commentRepository.save(postComment);
        return dtoMapper.postToDto(postRepository.findById(post.getId()).get());
    }

    @Override
    public List<PostCommentDTO> getPostComments(int postId, int pageNumber) {
        if(!postRepository.existsById(postId)){
            throw new IllegalArgumentException("Wrong post ID");
        }


        return commentRepository.findByPostId(postId, PageRequest.of(pageNumber, 5)).orElseThrow()
                .stream()
                .map(dtoMapper::commentToDto).toList();
    }


}
