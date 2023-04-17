package com.practice.social_network.controllers;

import com.practice.social_network.services.intefaces.PostService;
import com.practice.social_network.entities.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService service;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping(path = "/{userId}")
    public List<Post> getUserPosts(@PathVariable int userId) {
        return service.getUserPosts(userId);
    }

    @PostMapping(path = "/{userId}")
    public Post createPost(@PathVariable int userId, @RequestBody Post post) {
        return service.createPost(post, userId);
    }

    @PutMapping(path = "/{userId}")
    public Post updatePost(@PathVariable int userId, @RequestBody Post post) {
        return service.updatePost(post, userId);
    }

    @DeleteMapping(path = "/{userId}")
    public Post deletePost(@PathVariable int userId, @RequestParam int postId) {
        return service.deletePost(postId, userId);
    }

    @GetMapping(path = "/{userId}/news")
    public List<Post> getFriendsPosts(@PathVariable int userId, @RequestParam int pageNumber) {
        return service.getFriendsPosts(userId, pageNumber);
    }

}
