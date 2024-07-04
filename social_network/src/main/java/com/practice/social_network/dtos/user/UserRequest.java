package com.practice.social_network.dtos.user;

import com.practice.social_network.dtos.AbstractRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRequest extends AbstractRequest {

    @NotBlank
    private String fullName;

    @NotBlank
    private String nickname;

    @NotBlank
    private String email;
    private String password;

}
