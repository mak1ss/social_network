package com.practice.social_network.dtos.post;

import com.practice.social_network.dtos.AbstractResponse;
import com.practice.social_network.dtos.user.UserResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostResponse extends AbstractResponse {

    private UserResponse user;
    private LocalDateTime creationDate;
    private String postBody;
    private Integer likes;
    private Integer comments;
}

