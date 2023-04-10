package com.practice.social_network.db;

import com.practice.social_network.entities.User;
import com.practice.social_network.repositories.UserRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserCrudTests {

    private ApplicationContext context;

    private UserRepository repository;

    private User user;

    @Before
    public void init() {
        this.context = new ClassPathXmlApplicationContext("db_test_config.xml");
        this.repository = context.getBean("userRepository", UserRepository.class);
    }

    @After
    public void cleanDb() {
        try {
            repository.deleteTestRows();
        } catch (IllegalArgumentException ex) {
            System.out.println("Database is already clear");
        }
        this.user = null;
    }


    private void insertUser() {
        User user = new User();
        user.setFullName("test_name");
        user.setNickname("nickname");
        user.setEmail("email@gmail.com");
        user.setPassword("password");
        this.user = repository.save(user);
    }

    @Test
    public void initTest() {
        assertNotNull(context);
        assertNotNull(repository);
    }

    @Test
    public void insertTest() {
        User newUser = new User();
        newUser.setFullName("test_name");
        newUser.setNickname("nickname");
        newUser.setEmail("email@gmail.com");
        newUser.setPassword("password");
        repository.save(newUser);
    }

    @Test
    public void selectTest() {
        insertUser();
        Optional<User> foundUser = repository.findById(user.getId());
        assertTrue(foundUser.isPresent());
    }

    @Test
    public void followTest() {
        insertUser();
        User userToFollow = new User();
        userToFollow.setFullName("test_name");
        userToFollow.setNickname("otherUserNickName");
        userToFollow.setPassword("password");
        userToFollow.setEmail("other@gmail.com");
        repository.save(userToFollow);

        user.addFollowing(userToFollow);
        user = repository.save(user);
        assertFalse(user.getFollowings().isEmpty());
    }

    @Test
    public void deleteUserTest(){
        insertUser();

        repository.delete(user);
    }
}
