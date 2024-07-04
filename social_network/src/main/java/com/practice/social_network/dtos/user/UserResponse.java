package com.practice.social_network.dtos.user;

import com.practice.social_network.dtos.AbstractResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserResponse extends AbstractResponse {

    private Integer id;
    private String fullName;
    private String nickname;
    private String email;
}
