package com.practice.social_network.controllers;

import com.practice.social_network.dtos.postComment.PostCommentRequest;
import com.practice.social_network.dtos.postComment.PostCommentResponse;
import com.practice.social_network.services.intefaces.PostCommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-comment")
@AllArgsConstructor
public class PostCommentController {

    private PostCommentService service;

    @GetMapping
    public List<PostCommentResponse> getAll(@RequestParam Integer postId, @RequestParam Integer pageNumber) {
        return service.getPostComments(postId, pageNumber);
    }

    @GetMapping("/{commentId}")
    public PostCommentResponse getRecord(@PathVariable Integer commentId) {
        return service.getPostComment(commentId);
    }

    @PostMapping
    public PostCommentResponse createRecord(@Valid @RequestBody PostCommentRequest request) {
        return service.createPostComment(request);
    }

    @PutMapping
    public PostCommentResponse updateRecord(@Valid @RequestBody PostCommentRequest request) {
        return service.updatePostComment(request);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Object> deleteRecord(@PathVariable Integer commentId) {
        service.deletePostComment(commentId);
        return ResponseEntity.ok().build();
    }
}
