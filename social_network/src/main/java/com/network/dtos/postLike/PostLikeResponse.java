package com.network.dtos.postLike;

import com.network.dtos.user.UserResponse;
import com.network.dtos.AbstractResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=false)
public class PostLikeResponse extends AbstractResponse {

    private Integer id;
    private UserResponse user;
    private LocalDateTime likedAt;
    private Boolean archived;
}
