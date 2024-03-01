package com.practice.social_network.controllers;

import com.practice.social_network.dtos.PostCommentDTO;
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

    @PutMapping(path = "/{userId}/{postId}")
    public PostDTO updatePost(@PathVariable int userId, @PathVariable int postId, @RequestParam String newPostBody) {
        PostDTO dto = new PostDTO();
        dto.setId(postId);
        dto.setPostBody(newPostBody);
        return service.updatePost(dto, userId);
    }

    @DeleteMapping(path = "/{userId}")
    public PostDTO deletePost(@PathVariable int userId, @RequestParam int postId) {
        return service.deletePost(postId, userId);
    }

    @GetMapping(path = "/{userId}/news")
    public List<PostDTO> getFriendsPosts(@PathVariable int userId, @RequestParam int pageNumber) {
        return service.getFriendsPosts(userId, pageNumber);
    }

    @PutMapping(path = "/{postId}/like")
    public PostDTO likePost(@PathVariable int postId, @RequestParam int userId) {
        return service.likePost(userId, postId);
    }

    @PutMapping(path = "/{postId}/comment")
    public PostDTO leaveComment(@PathVariable int postId, @RequestParam int userId, @RequestParam String commentBody){
        return service.leaveComment(commentBody, postId, userId);
    }

    @GetMapping(path="/{postId}/comments")
    public List<PostCommentDTO> getPostComments(@PathVariable int postId, @RequestParam int pageNumber){
        return service.getPostComments(postId, pageNumber);
    }
}
