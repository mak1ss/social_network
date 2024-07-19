package com.practice.social_network.dtos.user;

import com.practice.social_network.dtos.AbstractRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserRequest extends AbstractRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String nickname;

    @NotBlank
    @Email
    private String email;

    private String password;

}
