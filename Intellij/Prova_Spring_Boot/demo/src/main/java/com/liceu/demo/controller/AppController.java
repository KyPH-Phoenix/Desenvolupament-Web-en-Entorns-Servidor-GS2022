package com.liceu.demo.controller;

import com.liceu.demo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AppController {
    @GetMapping("/")
    public String index() {
        return "navbar";
    }



    @GetMapping("/test")
    public String test(Model model) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Juan", 25));
        people.add(new Person("Toni", 27));
        people.add(new Person("Andreu", 32));

        model.addAttribute("people", people);
        model.addAttribute("name", "Hugo");

        model.addAttribute("page", 5);
        model.addAttribute("pname", "bill");

        model.addAttribute("prova", "<script>console.log('caca do vaca')</script>");

        return "index";
    }
}
