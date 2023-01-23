package com.liceu.objects.controller;

import com.liceu.objects.exception.DistinctPasswordException;
import com.liceu.objects.exception.UsernameAlreadyExistsException;
import com.liceu.objects.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.net.http.HttpRequest;

@Controller
public class AppController {
    @Autowired
    UserService userService;

    @GetMapping("/")
    public RedirectView redirectToLogin() {
        return new RedirectView("/login");
    }

    @GetMapping("/login")
    public String loginGet() {

        return "login";
    }

    @GetMapping("/register")
    public String registerGet() {

        return "register";
    }

    @PostMapping("/register")
    public RedirectView registerPost(HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String rPassword = req.getParameter("rPassword");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String email = req.getParameter("email");

        try {
            userService.registerUser(username, password, rPassword, firstname, lastname, email);
        } catch (DistinctPasswordException e) {
            // Password distinta
        } catch (UsernameAlreadyExistsException e) {
            // Username repetido
        }

        return new RedirectView("/login");
    }
}
