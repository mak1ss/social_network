package com.practice.social_network.controllers;

import com.practice.social_network.dtos.PostDTO;
import com.practice.social_network.services.intefaces.PostService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@SecurityRequirement(name = "networkScheme")
public class PostController {

    private final PostService service;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping(path = "/{userId}")
    public List<PostDTO> getUserPosts(@PathVariable int userId) {
        return service.getUserPosts(userId);
    }

    @PostMapping(path = "/{userId}")
    public PostDTO createPost(@PathVariable int userId, @RequestBody PostDTO post) {
        return service.createPost(post, userId);
    }

    @PutMapping(path = "/{userId}")
    public PostDTO updatePost(@PathVariable int userId, @RequestBody PostDTO post) {
        return service.updatePost(post, userId);
    }

    @DeleteMapping(path = "/{userId}")
    public PostDTO deletePost(@PathVariable int userId, @RequestParam int postId) {
        return service.deletePost(postId, userId);
    }

    @GetMapping(path = "/{userId}/news")
    public List<PostDTO> getFriendsPosts(@PathVariable int userId, @RequestParam int pageNumber) {
        return service.getFriendsPosts(userId, pageNumber);
    }

}
