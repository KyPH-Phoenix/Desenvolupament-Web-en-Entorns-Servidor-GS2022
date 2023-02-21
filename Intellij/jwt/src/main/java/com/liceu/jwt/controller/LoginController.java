package com.liceu.jwt.controller;

import com.liceu.jwt.model.LoginForm;
import com.liceu.jwt.service.TokenService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    TokenService tokenService;

    public LoginController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    @CrossOrigin
    public Map<String, String> login(@RequestBody LoginForm loginForm) {
        String user = loginForm.getUser();
        String password = loginForm.getPassword();

        if ((user.equals("bill") && password.equals("gates"))) {
            String token = tokenService.newToken(user);
            Map<String, String> map = new HashMap<>();
            map.put("token", token);
            return map;
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/private")
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, String> test(HttpServletRequest req) {
        String username = (String) req.getAttribute("username");
        Map<String, String> map = new HashMap<>();
        map.put("message", "secret");
        map.put("username", username);
        return map;
    }
}
