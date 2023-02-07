package com.liceu.objects.controller;

import com.liceu.objects.exception.DistinctPasswordException;
import com.liceu.objects.exception.IncorrectPasswordOrUsernameException;
import com.liceu.objects.exception.UsernameAlreadyExistsException;
import com.liceu.objects.model.User;
import com.liceu.objects.service.UserService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    @GetMapping("/")
    public RedirectView redirectToLogin() {
        return new RedirectView("/login");
    }

    @GetMapping("/login")
    public String loginGet() {

        return "login";
    }

    @PostMapping("/login")
    public RedirectView loginPost(String username, String password) {

        try {
            User user = userService.getUserByUsername(username, password);
            session.setAttribute("user", user);
        } catch (IncorrectPasswordOrUsernameException e) {
            // Incorrect Password
            System.out.println("Error, usuario o contraseña incorrecto");
            return new RedirectView("/login");
        }

        return new RedirectView("/objects");
    }

    @GetMapping("/signup")
    public String registerGet() {

        return "signup";
    }

    @PostMapping("/signup")
    public RedirectView registerPost(String username, String password, String rPassword,
                                     String firstname, String lastname, String email) {
        try {
            userService.registerUser(username, password, rPassword, firstname, lastname, email);
        } catch (DistinctPasswordException e) {
            // Password distinta
            System.out.println("Password distinta");
        } catch (UsernameAlreadyExistsException e) {
            // Username repetido
            System.out.println("Usrname repe");
        }

        return new RedirectView("/login");
    }

    @GetMapping("/logout")
    public RedirectView logout() {
        session.removeAttribute("user");

        return new RedirectView("/login");
    }
}
