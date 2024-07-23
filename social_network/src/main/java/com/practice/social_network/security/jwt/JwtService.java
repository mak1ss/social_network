package com.practice.social_network.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@Slf4j
public class JwtService {

    @Value("${com.practice.social_network.jwt.jwtSecret}")
    private String key;

    @Value("${com.practice.social_network.jwt.jwtExpirationMinutes}")
    public static Integer JWT_EXPIRATION_MINUTES;

    @Value("${com.practice.social_network.jwt.refreshTokenExpirationMinutes}")
    public static Integer REFRESH_TOKEN_EXPIRATION_MINUTES;


    public String generateToken(String email) {
        LocalDateTime expirationDateTime = LocalDateTime.now().plusMinutes(JWT_EXPIRATION_MINUTES);
        Date expirationDate = Date.from(expirationDateTime.atZone(ZoneId.systemDefault()).toInstant());

        SecretKey signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));

        return Jwts.builder()
                .subject(email)
                .expiration(expirationDate)
                .signWith(signingKey)
                .compact();
    }

    public String getEmailFromToken(String token) {
        SecretKey signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        Claims claims = Jwts.parser().verifyWith(signingKey).build().parseSignedClaims(token).getPayload();

        return claims.getSubject();
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return token.substring(7);
        }

        return null;
    }
}
