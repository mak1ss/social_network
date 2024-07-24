package com.network.controllers;

import com.network.filtering.model.EntityFilterSpecificationBuilder;
import com.network.filtering.model.postComment.PostCommentSpecificationBuilder;
import com.network.mappers.Mapper;
import com.network.mappers.PostCommentMapper;
import com.network.model.PostComment;
import com.network.services.AbstractService;
import com.network.dtos.postComment.PostCommentRequest;
import com.network.dtos.postComment.PostCommentResponse;
import com.network.services.PostCommentService;
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
