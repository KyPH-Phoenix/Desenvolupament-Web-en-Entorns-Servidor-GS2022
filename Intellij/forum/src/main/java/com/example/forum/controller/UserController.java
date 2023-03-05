package com.example.forum.controller;

import com.example.forum.model.User;

import com.example.forum.service.CategoryService;
import com.example.forum.service.TokenService;
import com.example.forum.service.UserService;
import com.example.forum.utilities.Util;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    UserService userService;
    TokenService tokenService;
    CategoryService categoryService;

    public UserController(UserService userService, TokenService tokenService, CategoryService categoryService) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.categoryService = categoryService;
    }

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, String> register(@RequestBody User user, HttpServletResponse res) {
        Map<String, String> map = new HashMap<>();

        if (userAlreadyExists(user)) {
            map.put("messsage", "User already exists. Cannot repeat email");
            res.setStatus(400);
        } else {
            User u = new User();
            BeanUtils.copyProperties(user, u);
            userService.save(u);
            map.put("message", "SUCCES");
        }

        return map;
    }

    private boolean userAlreadyExists(User user) {
        List<User> users = userService.findByEmailLike(user.getEmail());

        return !users.isEmpty();
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, Object> login(@RequestBody User user, HttpServletResponse res) {
        Map<String, Object> map = new HashMap<>();

        if (userOk(user)) {
            User u = userService.findByEmailLike(user.getEmail()).get(0);
            String token = tokenService.newToken(u);

            map.put("token", token);
            map.put("user", Util.buildUserMap(u, categoryService.getAllCategories()));
        } else {
            map.put("message", "Incorrect email or password");
            res.setStatus(400);
        }

        return map;
    }

    @GetMapping("/getprofile")
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, Object> getProfile(HttpServletRequest req) {
        String email = (String) req.getAttribute("email");

        User u = userService.findByEmailLike(email).get(0);
        long iat = (long) req.getAttribute("iat");

        Map<String, Object> map = Util.buildUserMap(u, categoryService.getAllCategories());
        map.put("iat", iat);

        return map;
    }

    private boolean userOk(User user) {
        List<User> users = userService.findByEmailLike(user.getEmail());

        if (users.isEmpty()) return false;

        return users.get(0).getPassword().equals(user.getPassword());
    }

    @PutMapping("/profile")
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, Object> updateProfile(@RequestHeader("Authorization") String auth, @RequestBody User userInfo) {
        String token = auth.replace("Bearer ", "");
        String email = userInfo.getEmail();
        String name = userInfo.getName();

        User user = userService.findByEmailLike(tokenService.getEmail(token)).get(0);

        userService.updateUserData(email, name, user.getId());

        user.setEmail(email);
        user.setName(name);

        Map<String, Object> map = new HashMap<>();

        map.put("token", token);
        map.put("user", Util.buildUserMap(user, categoryService.getAllCategories()));

        return map;
    }
}
