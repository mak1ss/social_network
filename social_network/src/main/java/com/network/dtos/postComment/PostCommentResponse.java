package com.network.dtos.postComment;

import com.network.dtos.user.UserResponse;
import com.network.dtos.AbstractResponse;
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
