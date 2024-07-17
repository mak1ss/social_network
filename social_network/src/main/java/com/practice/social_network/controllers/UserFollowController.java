package com.practice.social_network.controllers;

import com.practice.social_network.dtos.userFollow.UserFollowRequest;
import com.practice.social_network.dtos.userFollow.UserFollowResponse;
import com.practice.social_network.filtering.model.EntityFilterSpecificationBuilder;
import com.practice.social_network.filtering.model.userFollow.UserFollowSpecificationBuilder;
import com.practice.social_network.mappers.Mapper;
import com.practice.social_network.mappers.UserFollowMapper;
import com.practice.social_network.model.UserFollow;
import com.practice.social_network.services.AbstractService;
import com.practice.social_network.services.UserFollowService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-follow")
@AllArgsConstructor
public class UserFollowController extends AbstractController<UserFollow, UserFollowRequest, UserFollowResponse> {

    private final UserFollowService service;
    private final UserFollowMapper mapper;
    private final UserFollowSpecificationBuilder specificationBuilder;

    @Override
    protected AbstractService<UserFollow> getService() {
        return service;
    }

    @Override
    protected Mapper<UserFollow, UserFollowResponse, UserFollowRequest> getMapper() {
        return mapper;
    }

    @Override
    protected EntityFilterSpecificationBuilder<UserFollow> getSpecificationBuilder() {
        return specificationBuilder;
    }

}
