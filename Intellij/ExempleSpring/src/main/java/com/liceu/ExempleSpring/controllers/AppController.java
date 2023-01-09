package com.liceu.ExempleSpring.controllers;

import com.liceu.ExempleSpring.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AppController {
    @Autowired
    MyService service;

    @RequestMapping("/test/{num1}/{num2}")
    public String test(@PathVariable int num1, @PathVariable int num2, Model model) {
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);

        int num3 = service.addNumbers(num1, num2);

        model.addAttribute("message", num3);

        return "test";
    }

    @RequestMapping("/hello")
    public String hello(HttpSession session) {
        Object o = session.getAttribute("counter");
        Integer i = 1;

        if (o != null) {
            i = (Integer) o;
            i++;
        }

        session.setAttribute("counter", i);

        return "hello";
    }

    @GetMapping("/test2")
    @ResponseBody
    public String test2() {
        return "Hello from spring";
    }
}
