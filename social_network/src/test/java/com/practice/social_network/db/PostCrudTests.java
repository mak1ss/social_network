package com.practice.social_network.db;

import com.practice.social_network.entities.Post;
import com.practice.social_network.entities.User;
import com.practice.social_network.repositories.PostRepository;
import com.practice.social_network.repositories.UserRepository;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class PostCrudTests {
    private PostRepository postRepository;
    private UserRepository userRepository;
    private ApplicationContext context;
    private User user;

    private Post addPost() {
        Post post = new Post();
        post.setUser(user);
        post.setPostBody("body_of_test_post");
        post.setCreationDate(new Timestamp(System.currentTimeMillis()));
        post.setUser(user, false);
        return post;
    }

    @Before
    public void init() {
        this.context = new ClassPathXmlApplicationContext("db_test_config.xml");
        this.postRepository = context.getBean("postRepository", PostRepository.class);
        this.userRepository = context.getBean("userRepository", UserRepository.class);

        User user1 = new User();
        user1.setFullName("test_name");
        user1.setNickname("nickname");
        user1.setEmail("email@gmail.com");
        user1.setPassword("password");
        user = userRepository.save(user1);
    }


    @After
    public void cleanDb() {
        try {
            userRepository.deleteTestRows();
        } catch (IllegalArgumentException ex) {
            System.out.println("Database is already clear");
        }
        user = null;
    }

    @Test
    public void initTest() {
        assertNotNull(context);
        assertNotNull(userRepository);
        assertNotNull(postRepository);
        assertNotNull(user);
    }

    @Test
    public void insertPostTest() {
        Post post = addPost();
        postRepository.save(post);
    }

    @Test
    public void deletePostTest() {
        Post post = addPost();
        post = postRepository.save(post);
        Optional<List<Post>> allPosts = postRepository.findByUserId(user.getId());
        assertTrue(allPosts.isPresent());
        int prevSize = allPosts.get().size();
        postRepository.deleteById(post.getId());

        allPosts = postRepository.findByUserId(user.getId());
        assertNotEquals(prevSize, allPosts.get().size());
    }

    @Test
    public void updatePostTest() {
        Post post = addPost();
        post = postRepository.save(post);

        post.setPostBody("updated_post_body");

        Post updatedPost = postRepository.save(post);
        assertEquals(updatedPost.getPostBody(), "updated_post_body");
    }


}
