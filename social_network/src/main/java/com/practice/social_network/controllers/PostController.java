package com.practice.social_network.controllers;

import com.practice.social_network.dtos.post.PostResponse;
import com.practice.social_network.dtos.post.PostRequest;
import com.practice.social_network.dtos.postComment.PostCommentResponse;
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
    public List<PostResponse> getUserPosts(@PathVariable Integer userId) {
        return service.getUserPosts(userId);
    }

    @PostMapping
    public PostResponse createPost(@RequestBody PostRequest post) {
        return service.createPost(post);
    }

    @PutMapping(path = "/{postId}")
    public PostResponse updatePost(@PathVariable Integer postId, @RequestParam String newPostBody) {
        PostRequest dto = new PostRequest();
        dto.setId(postId);
        dto.setPostBody(newPostBody);
        return service.updatePost(dto);
    }

    @DeleteMapping
    public PostResponse deletePost(@RequestParam Integer postId) {
        return service.deletePost(postId);
    }

    @GetMapping(path = "/{userId}/news")
    public List<PostResponse> getFriendsPosts(@PathVariable Integer userId, @RequestParam Integer pageNumber) {
        return service.getFriendsPosts(userId, pageNumber);
    }

    @PutMapping(path = "/{postId}/like")
    public PostResponse likePost(@PathVariable Integer postId, @RequestParam Integer userId) {
        return service.likePost(userId, postId);
    }
}
