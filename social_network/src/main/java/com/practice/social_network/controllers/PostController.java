package com.practice.social_network.controllers;

import com.practice.social_network.services.intefaces.PostService;
import com.practice.social_network.entities.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable int userId){
        return ResponseEntity.status(HttpStatus.OK).body(service.getUserPosts(userId));
    }

    @PostMapping(path = "/{userId}")
    public ResponseEntity<Post> createPost(@PathVariable int userId, @RequestBody Post post){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createPost(post, userId));
    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<Post> updatePost(@PathVariable int userId, @RequestBody Post post){
        return ResponseEntity.status(HttpStatus.OK).body(service.updatePost(post, userId));
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<Post> deletePost(@PathVariable int userId, @RequestParam int postId){
        return ResponseEntity.status(HttpStatus.OK).body(service.deletePost(postId, userId));
    }

    @GetMapping(path = "/{userId}/news")
    public ResponseEntity<List<Post>> getFriendsPosts(@PathVariable int userId, @RequestParam int pageNumber){
        return ResponseEntity.status(HttpStatus.OK).body(service.getFriendsPosts(userId, pageNumber));
    }


//    @ExceptionHandler(IllegalArgumentException.class)
//    public ResponseEntity<String> wrongIdHandler(IllegalArgumentException ex){
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getLocalizedMessage());
//    }

}
