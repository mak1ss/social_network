package com.network.dtos.userFollow;

import com.network.dtos.user.UserResponse;
import com.network.dtos.AbstractResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserFollowResponse extends AbstractResponse {

    private Integer id;
    private UserResponse follower;
    private UserResponse followed;
    private LocalDateTime subscriptionDate;
    private Boolean archived;

}
