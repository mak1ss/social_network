package com.practice.social_network.controllers;

import com.practice.social_network.dtos.follow.FollowRequest;
import com.practice.social_network.dtos.follow.FollowResponse;
import com.practice.social_network.services.intefaces.FollowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user-follow")
@AllArgsConstructor
public class FollowController {

    private final FollowService followService;

    @GetMapping("/{userId}/{type}")
    public List<FollowResponse> getUserRelationships(@PathVariable Integer userId, @PathVariable RelationshipType type) {
        List<FollowResponse> entities = new ArrayList<>();
        switch (type) {
            case FOLLOWERS -> entities = followService.getUserFollowers(userId);
            case FOLLOWS -> entities = followService.getUserFollows(userId);
        }
        return entities;
    }

    @PostMapping
    public FollowResponse createFollow(@RequestBody FollowRequest followRequest) {
        return followService.createFollow(followRequest);
    }

    @DeleteMapping("/{followId}")
    public ResponseEntity<Object> unfollow(@PathVariable Integer followId) {
        followService.deleteFollow(followId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
