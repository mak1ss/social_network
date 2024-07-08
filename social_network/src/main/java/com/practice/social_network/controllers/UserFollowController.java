package com.practice.social_network.controllers;

import com.practice.social_network.dtos.userFollow.UserFollowRequest;
import com.practice.social_network.dtos.userFollow.UserFollowResponse;
import com.practice.social_network.services.intefaces.UserFollowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user-follow")
@AllArgsConstructor
public class UserFollowController {

    private final UserFollowService followService;

    @GetMapping("/{userId}/{type}")
    public List<UserFollowResponse> getUserRelationships(@PathVariable Integer userId, @PathVariable RelationshipType type) {
        List<UserFollowResponse> entities = new ArrayList<>();
        switch (type) {
            case FOLLOWERS -> entities = followService.getUserFollowers(userId);
            case FOLLOWS -> entities = followService.getUserFollows(userId);
        }
        return entities;
    }

    @PostMapping
    public UserFollowResponse createFollow(@RequestBody UserFollowRequest followRequest) {
        return followService.createFollow(followRequest);
    }

    @DeleteMapping("/{followId}")
    public ResponseEntity<Object> unfollow(@PathVariable Integer followId) {
        followService.deleteFollow(followId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
