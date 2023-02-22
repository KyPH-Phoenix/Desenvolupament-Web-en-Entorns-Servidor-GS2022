package com.example.forum.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.forum.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {
    @Value("${token.secret}")
    String tokenSecret;

    @Value("${token.expire}")
    long tokenExpire;

    public String newToken(User user) {
        Map<String, String> map = new HashMap<>();
        map.put("role", user.getRole());
        map.put("id", "" + user.getId());
        map.put("email", user.getEmail());
        map.put("name", user.getName());

        return JWT.create()
                .withPayload(map)
                .withExpiresAt(new Date(System.currentTimeMillis() + tokenExpire))
                .sign(Algorithm.HMAC512(tokenSecret.getBytes()));
    }

    public String getUser(String token) {
        return JWT.require(Algorithm.HMAC512(tokenSecret.getBytes()))
                .build()
                .verify(token)
                .getSubject();
    }
}
