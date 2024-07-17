package com.practice.social_network.controllers;

import com.practice.social_network.dtos.postLike.PostLikeRequest;
import com.practice.social_network.dtos.postLike.PostLikeResponse;
import com.practice.social_network.filtering.model.EntityFilterSpecificationBuilder;
import com.practice.social_network.filtering.model.postLike.PostLikeSpecificationBuilder;
import com.practice.social_network.mappers.Mapper;
import com.practice.social_network.mappers.PostLikeMapper;
import com.practice.social_network.model.PostLike;
import com.practice.social_network.services.AbstractService;
import com.practice.social_network.services.PostLikeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/post-like")
public class PostLikeController extends AbstractController<PostLike, PostLikeRequest, PostLikeResponse> {

    private final PostLikeService service;
    private final PostLikeMapper mapper;
    private final PostLikeSpecificationBuilder specificationBuilder;

    @Override
    protected AbstractService<PostLike> getService() {
        return service;
    }

    @Override
    protected Mapper<PostLike, PostLikeResponse, PostLikeRequest> getMapper() {
        return mapper;
    }

    @Override
    protected void executeEntityDelete(Integer id) {
        getService().deleteById(id, true);
    }

    @Override
    protected EntityFilterSpecificationBuilder<PostLike> getSpecificationBuilder() {
        return specificationBuilder;
    }

}
