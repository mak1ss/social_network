package com.practice.social_network.datagen;

import com.practice.social_network.entities.Follow;
import com.practice.social_network.entities.Post;
import com.practice.social_network.entities.PostComment;
import com.practice.social_network.entities.User;
import com.practice.social_network.repositories.FollowRepository;
import com.practice.social_network.repositories.PostCommentRepository;
import com.practice.social_network.repositories.PostRepository;
import com.practice.social_network.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@AllArgsConstructor
@Service
public class TestDataGenerator {
    private static final Logger log = LoggerFactory.getLogger(TestDataGenerator.class);

    private UserRepository userRepository;
    private PostRepository postRepository;
    private PostCommentRepository commentRepository;
    private FollowRepository followRepository;
    private PasswordEncoder passwordEncoder;

    private void setDefaultPostBody(Post post) {
        post.setPostBody("""
                Hi everybody.
                My name is
                """ + post.getUser().getNickname() + """
                , nice to meet you! :)""");
    }

    @PostConstruct
    private void initializeDbWIthTestData() {
        if (!userRepository.findAll().isEmpty()) {
            log.info("Skipped data generation. There is already data present in the database");
            return;
        }

        // User 1
        User user1 = new User();
        user1.setFullName("Vasia Volodymyrovich");
        user1.setNickname("vasi0k");
        user1.setEmail("vasia@gmail.com");
        user1.setPassword(passwordEncoder.encode("qwerty"));

        user1 = userRepository.save(user1);
        log.info("Saved user: " + user1);

        // User 2
        User user2 = new User();
        user2.setFullName("Maksym Volodymyrovich");
        user2.setNickname("mak1s");
        user2.setEmail("maks@gmail.com");
        user2.setPassword(passwordEncoder.encode("qwerty"));

        user2 = userRepository.save(user2);
        log.info("Saved user: " + user2);

        // User 3
        User user3 = new User();
        user3.setFullName("Olena Vasilivna");
        user3.setNickname("olen4");
        user3.setEmail("olena@gmail.com");
        user3.setPassword(passwordEncoder.encode("qwerty"));

        userRepository.save(user3);
        log.info("Saved user: " + user2);

        // Follow 1
        Follow follow1 = new Follow();
        follow1.setFollower(user1);
        follow1.setFollowed(user2);

        followRepository.save(follow1);
        log.info("Saved following : " + follow1);

        // Follow 2
        Follow follow2 = new Follow();
        follow2.setFollower(user2);
        follow2.setFollowed(user1);

        followRepository.save(follow2);
        log.info("Saved following : " + follow2);

        // Following 3
        Follow follow3 = new Follow();
        follow3.setFollower(user3);
        follow3.setFollowed(user2);

        followRepository.save(follow3);
        log.info("Saved following of: " + user1);

        // Following 4
        Follow follow4 = new Follow();
        follow4.setFollower(user3);
        follow4.setFollowed(user1);

        followRepository.save(follow4);
        log.info("Saved following of: " + user1);

        // Post 1 (owner - user 1, liked by user2 and user3)
        Post post1 = new Post();
        post1.setUser(user1);
        setDefaultPostBody(post1);
        post1.setCreationDate(LocalDateTime.now());
        post1.setLikes(Set.of(user2, user3));

        postRepository.save(post1);
        log.info("Saved post: " + post1);

        // Post 2 (owner - user 2, liked by user1 and user3)
        Post post2 = new Post();
        post2.setUser(user2);
        setDefaultPostBody(post2);
        post2.setCreationDate(LocalDateTime.now());
        post2.setLikes(Set.of(user1, user3));

        postRepository.save(post2);
        log.info("Saved post: " + post2);

        // Post 3 (owner - user 3, liked by user2 and user3)
        Post post3 = new Post();
        post3.setUser(user3);
        setDefaultPostBody(post3);
        post3.setCreationDate(LocalDateTime.now());
        post3.setLikes(Set.of(user1, user2));

        postRepository.save(post3);
        log.info("Saved post: " + post3);

        // Comment 1
        PostComment comment1 = new PostComment();
        comment1.setUser(user2);
        comment1.setCommentBody("Hi there from " + user2.getNickname());
        comment1.setPost(post1);
        comment1.setCreationDate(LocalDateTime.now());

        commentRepository.save(comment1);
        log.info("Saved comment for post " + comment1.getPost().getId() + ": " + comment1);

        // Comment 2
        PostComment comment2 = new PostComment();
        comment2.setUser(user3);
        comment2.setCommentBody("Hi there from " + user3.getNickname());
        comment2.setPost(post2);
        comment2.setCreationDate(LocalDateTime.now());

        commentRepository.save(comment2);
        log.info("Saved comment for post " + comment2.getPost().getId() + ": " + comment2);
        // Comment 3
        PostComment comment3 = new PostComment();
        comment3.setUser(user1);
        comment3.setCommentBody("Hi there from " + user1.getNickname());
        comment3.setPost(post3);
        comment3.setCreationDate(LocalDateTime.now());

        commentRepository.save(comment3);
        log.info("Saved comment for post " + comment3.getPost().getId() + ": " + comment3);
    }
}
