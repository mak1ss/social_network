package com.practice.social_network.dtos.post;

import com.practice.social_network.dtos.postComment.PostCommentResponse;
import com.practice.social_network.dtos.user.UserResponse;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class PostResponse {

    private Integer id;
    private UserResponse user;
    private LocalDateTime creationDate;
    private String postBody;
    private Integer likes;
    private List<PostCommentResponse> comments;
}
