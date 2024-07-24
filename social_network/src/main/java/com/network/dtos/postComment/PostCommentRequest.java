package com.network.dtos.postComment;

import com.network.dtos.AbstractRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@EqualsAndHashCode(callSuper = true)
@Data
public class PostCommentRequest extends AbstractRequest {

    @NotNull
    private Integer postId;

    @NotNull
    private Integer userId;

    @NotBlank
    @Size(max = 500, message = "User comment cannot be longer than 500 symbols")
    private String commentBody;

}
