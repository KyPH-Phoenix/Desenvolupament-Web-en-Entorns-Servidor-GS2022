package com.liceu.objects.controller;

import com.liceu.objects.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AppController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String loginGet() {

        return "login";
    }

    @GetMapping("/register")
    public String registerGet() {

        return "register";
    }

    @PostMapping("/register")
    public RedirectView registerPost(Model model) {

        return null;
    }
}
