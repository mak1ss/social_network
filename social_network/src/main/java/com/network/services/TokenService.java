package com.network.services;

import com.network.daos.AbstractDao;
import com.network.model.User;
import com.network.security.jwt.JwtService;
import com.network.daos.TokenDao;
import com.network.model.Token;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenService extends AbstractService<Token> {

    private TokenDao dao;
    private JwtService jwtService;

    @Override
    protected AbstractDao<Token> getDao() {
        return dao;
    }

    public String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }

    public Optional<Token> findByToken(String token) {
      return dao.findByToken(token);
    }

    public Optional<Token> findByUser(Integer userId) {
        return dao.findByUser(userId);
    }

    public boolean isTokenValid(Token token) {
        return token.getExpirationDate().isAfter(LocalDateTime.now());
    }

    public Token createOrUpdate(User user, String refreshToken) {
        Token token = findByUser(user.getId()).orElse(new Token());
        token.setUser(user);
        token.setRefreshToken(refreshToken);
        token.setExpirationDate(LocalDateTime.now().plusMinutes(jwtService.REFRESH_TOKEN_EXPIRATION_MINUTES));
        return save(token);
    }
}
