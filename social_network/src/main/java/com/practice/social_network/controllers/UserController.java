package com.practice.social_network.controllers;

import com.practice.social_network.entities.User;
import com.practice.social_network.services.intefaces.UserService;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController (UserService service){
        this.service = service;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> getAllUsers(){
       return ResponseEntity.status(HttpStatus.OK).body(service.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUser(user));
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User user){
        user.setId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(service.updateUser(user));
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable int userId){
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteUser(userId));
    }

    @PutMapping(path = "/{userId}/follow/")
    public ResponseEntity<User> followToUser(@PathVariable int userId, @RequestParam(name = "userToFollow") int userToFollowId){
        return ResponseEntity.status(HttpStatus.OK).body(service.followToUser(userId, userToFollowId));
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> wrongIdHandler(IllegalArgumentException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getLocalizedMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> notUniqueHandler(DataIntegrityViolationException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getLocalizedMessage());
    }
}
