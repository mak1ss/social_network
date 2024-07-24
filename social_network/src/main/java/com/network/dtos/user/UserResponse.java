package com.network.dtos.user;

import com.network.dtos.AbstractResponse;
import com.network.model.base.Role;
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
