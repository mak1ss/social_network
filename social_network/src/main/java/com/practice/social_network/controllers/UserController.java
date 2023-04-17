package com.practice.social_network.controllers;

import com.practice.social_network.entities.User;
import com.practice.social_network.services.intefaces.UserService;

import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(path = "/all")
    public List<User> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @PutMapping(path = "/{userId}")
    public User updateUser(@PathVariable int userId, @RequestBody User user) {
        user.setId(userId);
        return service.updateUser(user);
    }

    @DeleteMapping(path = "/{userId}")
    public User deleteUser(@PathVariable int userId) {
        return service.deleteUser(userId);
    }

    @PutMapping(path = "/{userId}/follow/")
    public User followToUser(@PathVariable int userId, @RequestParam(name = "userToFollow") int userToFollowId) {
        return service.followToUser(userId, userToFollowId);
    }

}
