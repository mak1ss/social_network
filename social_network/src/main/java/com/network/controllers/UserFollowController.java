package com.network.controllers;

import com.network.filtering.model.EntityFilterSpecificationBuilder;
import com.network.filtering.model.userFollow.UserFollowSpecificationBuilder;
import com.network.mappers.Mapper;
import com.network.mappers.UserFollowMapper;
import com.network.model.UserFollow;
import com.network.services.AbstractService;
import com.network.services.UserFollowService;
import com.network.dtos.userFollow.UserFollowRequest;
import com.network.dtos.userFollow.UserFollowResponse;
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
