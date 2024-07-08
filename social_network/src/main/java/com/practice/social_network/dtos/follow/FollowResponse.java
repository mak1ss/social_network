package com.practice.social_network.dtos.follow;

import com.practice.social_network.dtos.AbstractResponse;
import com.practice.social_network.dtos.user.UserResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class FollowResponse extends AbstractResponse {

    private Integer id;
    private UserResponse follower;
    private UserResponse followed;
    private LocalDateTime subscriptionDate;

}
