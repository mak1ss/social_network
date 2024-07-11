package com.practice.social_network.controllers;

import com.practice.social_network.dtos.postComment.PostCommentRequest;
import com.practice.social_network.dtos.postComment.PostCommentResponse;
import com.practice.social_network.mappers.Mapper;
import com.practice.social_network.mappers.PostCommentMapper;
import com.practice.social_network.model.PostComment;
import com.practice.social_network.services.AbstractService;
import com.practice.social_network.services.PostCommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post-comment")
@AllArgsConstructor
public class PostCommentController extends AbstractController<PostComment, PostCommentRequest, PostCommentResponse> {

    private PostCommentService service;
    private PostCommentMapper mapper;

    @Override
    protected AbstractService<PostComment> getService() {
        return service;
    }

    @Override
    protected Mapper<PostComment, PostCommentResponse, PostCommentRequest> getMapper() {
        return mapper;
    }

    @GetMapping
    public ResponseEntity<List<PostCommentResponse>> getByPostId(@RequestParam Integer postId, @RequestParam Integer pageNumber) {
        List<PostComment> entities = service.getPostComments(postId, pageNumber);
        return ResponseEntity.ok(mapper.entitiesToListResponse(entities));
    }

}
