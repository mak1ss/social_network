package com.practice.social_network.controllers;

import com.practice.social_network.dtos.post.PostResponse;
import com.practice.social_network.dtos.post.PostRequest;
import com.practice.social_network.services.intefaces.PostService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public List<PostResponse> getUserPosts(@PathVariable Integer userId) {
        return service.getUserPosts(userId);
    }

    @PostMapping
    public PostResponse createPost(@Valid @RequestBody PostRequest post) {
        return service.createPost(post);
    }

    @PutMapping
    public PostResponse updatePost(@Valid @RequestBody PostRequest request) {
        return service.updatePost(request);
    }

    @DeleteMapping(path="/{postId}")
    public ResponseEntity<Object> deletePost(@PathVariable Integer postId) {
        service.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}
