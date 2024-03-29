package com.practice.social_network.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int userId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp creationDate;

    private String postBody;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int likesAmount;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int comments;
}
