package com.practice.social_network.dtos.postLike;

import com.practice.social_network.dtos.AbstractRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper=true)
public class PostLikeRequest extends AbstractRequest {

    @NotNull
    private Integer postId;

    @NotNull
    private Integer userId;
    private LocalDateTime likedAt;
}
