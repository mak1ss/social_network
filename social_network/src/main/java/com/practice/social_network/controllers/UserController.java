package com.practice.social_network.controllers;

import com.practice.social_network.dtos.UserDTO;
import com.practice.social_network.services.intefaces.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
@SecurityRequirement(name = "networkScheme")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(path = "/all")
    public List<UserDTO> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserDTO user) {
        return service.createUser(user);
    }

    @PutMapping(path = "/{userId}")
    public UserDTO updateUser(@PathVariable int userId, @RequestBody UserDTO user) {
        user.setId(userId);
        return service.updateUser(user);
    }

    @DeleteMapping(path = "/{userId}")
    public UserDTO deleteUser(@PathVariable int userId) {
        return service.deleteUser(userId);
    }

    @PutMapping(path = "/{userId}/follow/")
    public UserDTO followToUser(@PathVariable int userId, @RequestParam(name = "userToFollow") int userToFollowId) {
        return service.followToUser(userId, userToFollowId);
    }

    @PutMapping(path = "/new-password")
    public UserDTO changePassword(@RequestParam(name = "userId") int userId, @RequestParam(name = "newPassword") String newPassword){
        return service.changeUserPassword(userId, newPassword);
    }
}
