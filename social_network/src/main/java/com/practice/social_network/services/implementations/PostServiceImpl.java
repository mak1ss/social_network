package com.practice.social_network.services.implementations;

import com.practice.social_network.entities.Post;
import com.practice.social_network.entities.User;
import com.practice.social_network.repositories.PostRepository;
import com.practice.social_network.repositories.UserRepository;
import com.practice.social_network.services.intefaces.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Post createPost(Post post, int userId) throws IllegalArgumentException {
        Optional<User> userById = userRepository.findById(userId);
        if (userById.isEmpty()) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        post.setUser(userById.get(), false);
        post.setCreationDate(new Timestamp(System.currentTimeMillis()));
        return postRepository.save(post);
    }

    @Override
    public Post updatePost(Post post, int userId) throws IllegalArgumentException {
        boolean isUserEmpty = userRepository.existsById(userId);
        boolean isPostEmpty = postRepository.existsById(post.getId());
        if (!(isUserEmpty || isPostEmpty)) {
            throw new IllegalArgumentException("Wrong user ID or post ID");
        }
        postRepository.updatePost(post.getPostBody(), post.getId(), userId);
        return postRepository.findById(post.getId()).get();
    }

    @Override
    public Post deletePost(int postId, int userId) throws IllegalArgumentException {
        boolean isUserEmpty = userRepository.existsById(userId);
        boolean isPostEmpty = postRepository.existsById(postId);
        if (!(isUserEmpty || isPostEmpty)) {
            throw new IllegalArgumentException("Wrong user ID or post ID");
        }
        return postRepository.deleteById(postId);
    }

    @Override
    public List<Post> getUserPosts(int userId) throws IllegalArgumentException {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        return postRepository.getPostsByUserId(userId);
    }

    @Override
    public List<Post> getFriendsPosts(int userId, int pageNumber) throws IllegalArgumentException {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException("Wrong user ID");
        }
        return postRepository.getFriendsPosts(userId, PageRequest.of(pageNumber, 20));
    }
}
