package com.practice.social_network.dtos.postComment;

import com.practice.social_network.dtos.user.UserResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCommentResponse {

    private Integer id;
    private UserResponse user;
    private String commentBody;
}
