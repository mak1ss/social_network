package com.practice.social_network.dtos.postLike;

import com.practice.social_network.dtos.AbstractResponse;
import com.practice.social_network.dtos.user.UserResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class PostLikeResponse extends AbstractResponse {

    private Integer id;
    private UserResponse user;
    private LocalDateTime likedAt;
}
