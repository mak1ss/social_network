package com.practice.social_network.controllers;

import com.practice.social_network.dtos.userFollow.UserFollowRequest;
import com.practice.social_network.dtos.userFollow.UserFollowResponse;
import com.practice.social_network.mappers.Mapper;
import com.practice.social_network.mappers.UserFollowMapper;
import com.practice.social_network.model.UserFollow;
import com.practice.social_network.services.AbstractService;
import com.practice.social_network.services.UserFollowService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user-follow")
@AllArgsConstructor
public class UserFollowController extends AbstractController<UserFollow, UserFollowRequest, UserFollowResponse> {

    private final UserFollowService service;
    private final UserFollowMapper mapper;

    @Override
    protected AbstractService<UserFollow> getService() {
        return service;
    }

    @Override
    protected Mapper<UserFollow, UserFollowResponse, UserFollowRequest> getMapper() {
        return mapper;
    }

    @GetMapping("/{userId}/{type}")
    public ResponseEntity<List<UserFollowResponse>> getUserRelationships(@PathVariable Integer userId, @PathVariable RelationshipType type) {
        List<UserFollow> entities = new ArrayList<>();
        switch (type) {
            case FOLLOWERS -> entities = service.getUserFollowers(userId);
            case FOLLOWS -> entities = service.getUserFollows(userId);
        }
        return ResponseEntity.ok(mapper.entitiesToListResponse(entities));
    }
}
