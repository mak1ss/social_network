package com.network.controllers;

import com.network.dtos.post.PostRequest;
import com.network.dtos.post.PostResponse;
import com.network.filtering.model.EntityFilterSpecificationBuilder;
import com.network.filtering.model.post.PostSpecificationBuilder;
import com.network.mappers.Mapper;
import com.network.mappers.PostMapper;
import com.network.model.Post;
import com.network.services.AbstractService;
import com.network.services.PostService;

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
