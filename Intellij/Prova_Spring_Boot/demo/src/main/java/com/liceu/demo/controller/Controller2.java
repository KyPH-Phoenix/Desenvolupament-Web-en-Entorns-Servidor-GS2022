package com.liceu.demo.controller;

import com.liceu.demo.model.Person;
import com.liceu.demo.model.PersonForm;
import com.liceu.demo.service.MyService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class Controller2 {
    @Autowired
    MyService service;

    @GetMapping("/people")
    public String people(Model model) {
        List<Person> people = service.getAllPeople();

        model.addAttribute("people", people);

        return "index";
    }

    @GetMapping("/person")
    public String personGet() {
        return "person";
    }

    @PostMapping("/person")
    public String personPost(@Valid PersonForm personForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("message", "Error");
        } else {
            service.newPerson(personForm.getName(), personForm.getAge());
            model.addAttribute("message", "Everything ok");
        }

        return "person";
    }
}
