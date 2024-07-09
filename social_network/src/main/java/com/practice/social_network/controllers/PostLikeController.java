package com.practice.social_network.controllers;

import com.practice.social_network.dtos.postLike.PostLikeRequest;
import com.practice.social_network.dtos.postLike.PostLikeResponse;
import com.practice.social_network.services.intefaces.PostLikeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/post-like")
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping
    public PostLikeResponse createPostLike(@Valid @RequestBody PostLikeRequest request) {
        return postLikeService.createPostLike(request);
    }

    @GetMapping
    public List<PostLikeResponse> getAllPostLikes(@RequestParam Integer postId) {
        return postLikeService.getPostLikes(postId);
    }

    @DeleteMapping
    public ResponseEntity<Object> deletePostLike(@Valid @RequestBody PostLikeRequest request) {
        postLikeService.deletePostLike(request);
        return ResponseEntity.ok().build();
    }
}
