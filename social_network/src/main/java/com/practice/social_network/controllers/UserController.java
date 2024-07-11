package com.practice.social_network.controllers;

import com.practice.social_network.dtos.user.ChangePasswordRequest;
import com.practice.social_network.dtos.user.UserRequest;
import com.practice.social_network.dtos.user.UserResponse;
import com.practice.social_network.mappers.Mapper;
import com.practice.social_network.mappers.UserMapper;
import com.practice.social_network.model.User;
import com.practice.social_network.services.AbstractService;
import com.practice.social_network.services.UserService;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@SecurityRequirement(name = "networkScheme")
@AllArgsConstructor
public class UserController extends AbstractController<User, UserRequest, UserResponse> {

    private final UserService userService;
    private final UserMapper mapper;

    @Override
    protected AbstractService<User> getService() {
        return userService;
    }

    @Override
    protected Mapper<User, UserResponse, UserRequest> getMapper() {
        return mapper;
    }

    @PutMapping(path = "/{userId}/new-password")
    public ResponseEntity<Object> changePassword(@PathVariable Integer userId, @Valid @RequestBody ChangePasswordRequest request){
        User user = userService.getById(userId).orElseThrow();
        if (userService.isPasswordsMatch(request.getOldPassword(), user.getPassword())) {
            user.setPassword(userService.encodePassword(request.getNewPassword()));
            userService.save(user);
            return ResponseEntity.ok(mapper.entityToResponse(user));
        }
        return ResponseEntity.badRequest().build();
    }
}
