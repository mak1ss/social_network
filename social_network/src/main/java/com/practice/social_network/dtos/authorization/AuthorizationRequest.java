package com.practice.social_network.dtos.authorization;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorizationRequest {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;
}
