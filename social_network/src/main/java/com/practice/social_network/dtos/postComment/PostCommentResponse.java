package com.practice.social_network.dtos.postComment;

import com.practice.social_network.dtos.AbstractResponse;
import com.practice.social_network.dtos.user.UserResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostCommentResponse extends AbstractResponse {

    private UserResponse user;
    private String commentBody;
    private LocalDateTime creationDate;
    private Boolean archived;
}
