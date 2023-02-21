package com.example.forum.controller;

import com.example.forum.model.User;

import com.example.forum.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping("/categories")
//    @CrossOrigin(origins = "http://localhost:3000")
//    public Map<String, String> categories() {
//        return "";
//    }

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

    public Map<String, Object> login(@RequestBody User user, HttpServletResponse res) {
        Map<String, Object> map = new HashMap<>();

        if (userOk(user)) {

        } else {
            map.put("message", "Incorrect email or password");
            res.setStatus(400);
        }

        return map;
    }

    private boolean userOk(User user) {
        List<User> users = userService.findByEmailLike(user.getEmail());

        if (users.isEmpty()) return false;

        return users.get(0).getPassword().equals(user.getPassword());
    }
}
