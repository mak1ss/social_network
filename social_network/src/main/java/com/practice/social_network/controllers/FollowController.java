package com.practice.social_network.controllers;

import com.practice.social_network.dtos.follow.FollowRequest;
import com.practice.social_network.dtos.follow.FollowResponse;
import com.practice.social_network.services.intefaces.FollowService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-follow")
@AllArgsConstructor
public class FollowController {

    private final FollowService followService;

    @GetMapping("/{followerId}")
    public List<FollowResponse> getFollows(@PathVariable Integer followerId) {
        return followService.getUserFollows(followerId);
    }

    @GetMapping("/{followedId}")
    public List<FollowResponse> getFollowers(@PathVariable Integer followedId) {
        return followService.getUserFollowers(followedId);
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
