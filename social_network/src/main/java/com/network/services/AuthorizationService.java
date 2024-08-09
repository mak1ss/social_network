package com.network.services;

import com.network.mappers.UserMapper;
import com.network.model.User;
import com.network.security.UserPrincipal;
import com.network.security.jwt.JwtService;
import com.network.dtos.authorization.AuthorizationResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorizationService {

    private JwtService jwtService;
    private UserMapper userMapper;
    private TokenService tokenService;
    private UserService userService;

    public AuthorizationResponse authorize(User user) {
        AuthorizationResponse response = new AuthorizationResponse();

        response.setAccessToken(jwtService.generateToken(user.getEmail()));
        response.setRefreshToken(tokenService.generateRefreshToken());
        response.setAccessTokenLifeTimeMinutes(jwtService.JWT_EXPIRATION_MINUTES);
        response.setRefreshTokenLifeTimeMinutes(jwtService.REFRESH_TOKEN_EXPIRATION_MINUTES);
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

    public boolean isOperationAuthorizedOrPerformedByAdmin(Integer userId) {
        String authorizedUserEmail = getAuthorizedUser().orElseThrow().getUsername();
        User authorizedUser = userService.findByEmail(authorizedUserEmail).orElseThrow();

        if(Objects.equals(authorizedUser.getId(), userId)) {
            return true;
        }

        return authorizedUser.isAdmin();
    }
}
