package com.practice.social_network.controllers;

import com.practice.social_network.dtos.user.ChangePasswordRequest;
import com.practice.social_network.dtos.user.UserRequest;
import com.practice.social_network.dtos.user.UserResponse;
import com.practice.social_network.services.intefaces.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<UserResponse> getAllUsers() {
        return service.getAllUsers();
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest user) {
        return service.createUser(user);
    }

    @PutMapping(path = "/{userId}")
    public UserResponse updateUser(@PathVariable Integer userId, @RequestBody UserRequest user) {
        user.setId(userId);
        return service.updateUser(user);
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Integer userId) {
        service.deleteUser(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PutMapping(path = "/{userId}/follow/")
    public UserResponse followToUser(@PathVariable Integer userId, @RequestParam(name = "userToFollow") int userToFollowId) {
        return service.followToUser(userId, userToFollowId);
    }

    @PutMapping(path = "/new-password")
    public UserResponse changePassword(@RequestParam(name = "userId") Integer userId, @RequestBody ChangePasswordRequest changePasswordRequest){
        return service.changeUserPassword(userId, changePasswordRequest);
    }
}
