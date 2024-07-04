package com.practice.social_network.dtos.post;

import com.practice.social_network.dtos.AbstractResponse;
import com.practice.social_network.dtos.postComment.PostCommentResponse;
import com.practice.social_network.dtos.user.UserResponse;
import com.practice.social_network.entities.Post;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostResponse extends AbstractResponse {

    private UserResponse user;
    private LocalDateTime creationDate;
    private String postBody;
    private Integer likes;
    private List<PostCommentResponse> comments;
}

