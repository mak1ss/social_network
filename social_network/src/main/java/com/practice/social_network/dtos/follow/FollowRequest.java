package com.practice.social_network.dtos.follow;

import com.practice.social_network.dtos.AbstractRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class FollowRequest extends AbstractRequest {

    @NotNull
    private Integer followerId;

    @NotNull
    private Integer followedId;

    private LocalDateTime subscriptionDate;
}
