package com.practice.social_network.dtos.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponse {

    private Integer id;
    private String fullName;
    private String nickname;
    private String email;
}
