package com.practice.social_network.controllers;

import com.practice.social_network.dtos.postComment.PostCommentRequest;
import com.practice.social_network.dtos.postComment.PostCommentResponse;
import com.practice.social_network.filtering.model.EntityFilterSpecificationBuilder;
import com.practice.social_network.filtering.model.postComment.PostCommentSpecificationBuilder;
import com.practice.social_network.mappers.Mapper;
import com.practice.social_network.mappers.PostCommentMapper;
import com.practice.social_network.model.PostComment;
import com.practice.social_network.services.AbstractService;
import com.practice.social_network.services.PostCommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post-comment")
@AllArgsConstructor
public class PostCommentController extends AbstractController<PostComment, PostCommentRequest, PostCommentResponse> {

    private PostCommentService service;
    private PostCommentMapper mapper;
    private PostCommentSpecificationBuilder specificationBuilder;
    @Override
    protected AbstractService<PostComment> getService() {
        return service;
    }

    @Override
    protected Mapper<PostComment, PostCommentResponse, PostCommentRequest> getMapper() {
        return mapper;
    }

    @Override
    protected EntityFilterSpecificationBuilder<PostComment> getSpecificationBuilder() {
        return specificationBuilder;
    }
}
