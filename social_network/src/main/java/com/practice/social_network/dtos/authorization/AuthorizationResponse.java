package com.practice.social_network.dtos.authorization;

import com.practice.social_network.dtos.user.UserResponse;
import lombok.Data;

@Data
public class AuthorizationResponse {

    private String accessToken;

    private String refreshToken;

    private Integer accessTokenLifeTimeMinutes;

    private Integer refreshTokenLifeTimeMinutes;

    private UserResponse user;
}
