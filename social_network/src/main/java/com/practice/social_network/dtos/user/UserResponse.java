package com.practice.social_network.dtos.user;

import com.practice.social_network.dtos.AbstractResponse;
import com.practice.social_network.model.base.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserResponse extends AbstractResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String nickname;
    private String email;
    private Role role;
    private Boolean archived;
}
