package com.practice.social_network.controllers;

import com.practice.social_network.dtos.postLike.PostLikeRequest;
import com.practice.social_network.dtos.postLike.PostLikeResponse;
import com.practice.social_network.services.intefaces.PostLikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/post-like")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping
    public PostLikeResponse createPostLike(@RequestBody PostLikeRequest request) {
        return postLikeService.createPostLike(request);
    }

    @DeleteMapping
    public ResponseEntity<Object> deletePostLike(@RequestBody PostLikeRequest request) {
        postLikeService.deletePostLike(request);
        return ResponseEntity.ok().build();
    }
}
