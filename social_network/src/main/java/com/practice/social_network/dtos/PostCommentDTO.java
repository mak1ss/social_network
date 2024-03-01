package com.practice.social_network.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCommentDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    private int userId;

    private String commentBody;
}
