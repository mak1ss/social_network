package com.practice.social_network.controllers;

import com.practice.social_network.dtos.post.PostResponse;
import com.practice.social_network.dtos.post.PostRequest;
import com.practice.social_network.filtering.model.EntityFilterSpecificationBuilder;
import com.practice.social_network.filtering.model.post.PostSpecificationBuilder;
import com.practice.social_network.mappers.Mapper;
import com.practice.social_network.mappers.PostMapper;
import com.practice.social_network.model.Post;
import com.practice.social_network.services.AbstractService;
import com.practice.social_network.services.PostService;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/posts")
@AllArgsConstructor
public class PostController extends AbstractController<Post, PostRequest, PostResponse> {

    private final PostService service;
    private final PostMapper mapper;
    private final PostSpecificationBuilder specificationBuilder;

    @Override
    protected AbstractService<Post> getService() {
        return service;
    }

    @Override
    protected Mapper<Post, PostResponse, PostRequest> getMapper() {
        return mapper;
    }

    @Override
    protected EntityFilterSpecificationBuilder<Post> getSpecificationBuilder() {
        return specificationBuilder;
    }

}
