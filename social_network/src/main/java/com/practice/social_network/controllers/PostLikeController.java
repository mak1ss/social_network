package com.practice.social_network.controllers;

import com.practice.social_network.dtos.postLike.PostLikeRequest;
import com.practice.social_network.dtos.postLike.PostLikeResponse;
import com.practice.social_network.mappers.Mapper;
import com.practice.social_network.mappers.PostLikeMapper;
import com.practice.social_network.model.PostLike;
import com.practice.social_network.services.AbstractService;
import com.practice.social_network.services.PostLikeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/post-like")
public class PostLikeController extends AbstractController<PostLike, PostLikeRequest, PostLikeResponse> {

    private final PostLikeService service;
    private final PostLikeMapper mapper;

    @Override
    protected AbstractService<PostLike> getService() {
        return service;
    }

    @Override
    protected Mapper<PostLike, PostLikeResponse, PostLikeRequest> getMapper() {
        return mapper;
    }

    @GetMapping
    public ResponseEntity<List<PostLikeResponse>> getAllPostLikes(@RequestParam Integer postId) {
        List<PostLike> entities = service.getPostLikes(postId);
        return ResponseEntity.ok(mapper.entitiesToListResponse(entities));
    }

    @Override
    protected void executeEntityDelete(Integer id) {
        getService().deleteById(id, true);
    }
}
