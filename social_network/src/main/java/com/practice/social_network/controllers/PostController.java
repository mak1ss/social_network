package com.practice.social_network.controllers;

import com.practice.social_network.dtos.post.PostResponse;
import com.practice.social_network.dtos.post.PostRequest;
import com.practice.social_network.mappers.Mapper;
import com.practice.social_network.mappers.PostMapper;
import com.practice.social_network.model.Post;
import com.practice.social_network.services.AbstractService;
import com.practice.social_network.services.PostService;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "/posts")
@AllArgsConstructor
public class PostController extends AbstractController<Post, PostRequest, PostResponse> {

    private final PostService service;
    private final PostMapper mapper;

    @Override
    protected AbstractService<Post> getService() {
        return service;
    }

    @Override
    protected Mapper<Post, PostResponse, PostRequest> getMapper() {
        return mapper;
    }

    @GetMapping
    public ResponseEntity<List<PostResponse>> getUserPosts(@RequestParam Integer userId) {
        List<Post> entities = service.getUserPosts(userId);
        return ResponseEntity.ok(mapper.entitiesToListResponse(entities));
    }
}
