package com.liceu.auth.controller;

import com.liceu.auth.service.GoogleService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class TestController {
    @Autowired
    GoogleService googleService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/logingoogle")
    public String loginGoogle() throws Exception {
        String url = googleService.getRedirect();
        return "redirect:" + url;
    }

    @GetMapping("/login/callback")
    public String callback(String code, Model model) throws Exception {
        String token = googleService.getAccesToken(code);

        Map<String, String> userDetails = googleService.getUserDetails(token);

        model.addAttribute("userdetails", userDetails);
        return "oauth";
    }

    @GetMapping("private")
    public String stuff(Model model, HttpSession session) {
        Object object = session.getAttribute("userdetails");
        if (object == null) throw new RuntimeException("User not auth");

        Map<String, String> userdetails = (Map<String, String>) object;

        model.addAttribute("userdetais", userdetails);
        return "oauth";
    }
}
