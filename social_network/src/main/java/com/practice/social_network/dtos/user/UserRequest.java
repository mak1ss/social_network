package com.practice.social_network.dtos.user;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Data
@NoArgsConstructor
public class UserRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    private String nickname;

    @NotBlank
    private String email;
    private String password;

}
