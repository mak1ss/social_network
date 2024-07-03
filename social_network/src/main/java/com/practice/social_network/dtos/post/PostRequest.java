package com.practice.social_network.dtos.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PostRequest {

    private Integer userId;

    @NotNull
    private LocalDateTime creationDate;

    @NotBlank
    @Size(max = 2000, message = "User post body cannot be longer than 2000 symbols")
    private String postBody;

}
