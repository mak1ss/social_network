package com.network.controllers;

import com.network.dtos.authorization.AuthorizationRequest;
import com.network.dtos.authorization.AuthorizationResponse;
import com.network.dtos.user.UserRequest;
import com.network.dtos.user.UserResponse;
import com.network.mappers.UserMapper;
import com.network.model.Token;
import com.network.model.User;
import com.network.services.AuthorizationService;
import com.network.services.TokenService;
import com.network.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class AuthorizationController {

    private UserService userService;
    private TokenService tokenService;
    private AuthorizationService authorizationService;
    private UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest request) {
        userService.checkEmailAccessibility(request.getEmail());

        User entity = userMapper.requestToEntity(request, Optional.empty());
        entity = userService.save(entity);

        UserResponse response = userMapper.entityToResponse(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthorizationResponse> login(@RequestBody AuthorizationRequest request) {
        Optional<User> user = userService.findByEmail(request.getEmail());

        if(user.isEmpty() || user.get().isArchived()) {
            throw new IllegalArgumentException("User with specified email address does not exist");
        }

        if(!userService.isPasswordsMatch(request.getPassword(), user.get().getPassword())) {
            throw new IllegalArgumentException("Incorrect password");
        }

        AuthorizationResponse response = authorizationService.authorize(user.get());
        tokenService.createOrUpdate(user.get(), response.getRefreshToken());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthorizationResponse> refresh(@RequestParam String refreshToken) {
        Optional<Token> tokenOptional = tokenService.findByToken(refreshToken);
        if(tokenOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        Token token = tokenOptional.get();
        if(!tokenService.isTokenValid(token)) {
            throw new IllegalArgumentException("Token life time expired");
        }

        AuthorizationResponse response = authorizationService.authorize(token.getUser());
        tokenService.createOrUpdate(token.getUser(), response.getRefreshToken());

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/logout")
    public ResponseEntity<Object> logout(@RequestParam String refreshToken) {
        tokenService.delete(tokenService.findByToken(refreshToken).orElseThrow());
        return ResponseEntity.noContent().build();
    }
}
