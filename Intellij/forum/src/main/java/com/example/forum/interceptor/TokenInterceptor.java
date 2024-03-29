package com.example.forum.interceptor;

import com.example.forum.service.TokenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.Instant;
import java.util.Base64;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Autowired
    TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("OPTIONS")) return true;

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        String token = authHeader.replace("Bearer ", "");
        long iat = tokenService.getExpiration(token);
        String email = tokenService.getEmail(token);

        request.setAttribute("email", email);
        request.setAttribute("iat", iat);
        return true;
    }
}
