package com.practice.social_network.dtos.postComment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
public class PostCommentRequest {

    private Integer userId;

    @NotBlank
    @Size(max = 500, message = "User comment cannot be longer than 500 symbols")
    private String commentBody;
}
