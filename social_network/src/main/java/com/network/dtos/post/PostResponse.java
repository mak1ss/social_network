package com.network.dtos.post;

import com.network.dtos.user.UserResponse;
import com.network.dtos.AbstractResponse;
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
    private Boolean archived;
}

