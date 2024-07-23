package com.practice.social_network.services;

import com.practice.social_network.dtos.authorization.AuthorizationResponse;
import com.practice.social_network.mappers.UserMapper;
import com.practice.social_network.model.User;
import com.practice.social_network.security.UserPrincipal;
import com.practice.social_network.security.jwt.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorizationService {

    private JwtService jwtService;
    private UserMapper userMapper;
    private TokenService tokenService;

    public AuthorizationResponse authorize(User user) {
        AuthorizationResponse response = new AuthorizationResponse();

        response.setAccessToken(jwtService.generateToken(user.getEmail()));
        response.setRefreshToken(tokenService.generateRefreshToken());
        response.setAccessTokenLifeTimeMinutes(JwtService.JWT_EXPIRATION_MINUTES);
        response.setRefreshTokenLifeTimeMinutes(JwtService.REFRESH_TOKEN_EXPIRATION_MINUTES);
        response.setUser(userMapper.entityToResponse(user));

        return response;
    }

    public Optional<UserPrincipal> getAuthorizedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null) {
            return Optional.empty();
        }

        return Optional.of((UserPrincipal) authentication.getPrincipal());
    }
}
