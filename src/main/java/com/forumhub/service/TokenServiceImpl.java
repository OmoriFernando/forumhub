package com.forumhub.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class TokenServiceImpl implements TokenService {
    @Value("${jwt.secret}")
    private String secretKey;

    private static final long EXPIRATION_TIME = 3600000;

    @Override
    public String gerarToken(String username) {
        Date agora = new Date();
        Date expiracao = new Date(agora.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(agora)
                .setExpiration(expiracao)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }
}
