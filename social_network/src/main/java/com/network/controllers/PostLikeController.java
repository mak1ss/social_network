package com.network.controllers;

import com.network.filtering.model.EntityFilterSpecificationBuilder;
import com.network.filtering.model.postLike.PostLikeSpecificationBuilder;
import com.network.mappers.Mapper;
import com.network.mappers.PostLikeMapper;
import com.network.model.PostLike;
import com.network.services.AbstractService;
import com.network.services.PostLikeService;
import com.network.dtos.postLike.PostLikeRequest;
import com.network.dtos.postLike.PostLikeResponse;
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
