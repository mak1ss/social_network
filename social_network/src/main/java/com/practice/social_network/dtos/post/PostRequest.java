package com.practice.social_network.dtos.post;

import com.practice.social_network.dtos.AbstractRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class PostRequest extends AbstractRequest {

    private Integer userId;

    private LocalDateTime creationDate;

    @NotBlank
    @Size(max = 2000, message = "User post body cannot be longer than 2000 symbols")
    private String postBody;

}
