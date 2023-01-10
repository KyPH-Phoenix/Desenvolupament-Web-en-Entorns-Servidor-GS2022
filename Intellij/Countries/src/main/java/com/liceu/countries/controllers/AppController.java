package com.liceu.countries.controllers;

import com.liceu.countries.model.City;
import com.liceu.countries.model.Country;
import com.liceu.countries.services.MyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@Controller
public class AppController {
    @Autowired
    MyService myService;

    @GetMapping("/countries")
    public String allCountries(Model model) {
        List<Country> countries = myService.getAllCountries();

        model.addAttribute("countries", countries);
        model.addAttribute("title", "ALL COUNTRIES");

        return "countries";
    }

    @GetMapping("/cities/{code}")
    public String cities(@PathVariable String code, Model model) {
        List<City> cities = myService.getCitiesFromCountry(code);
        String countryName = myService.getCountry(code).getName();

        model.addAttribute("cities", cities);
        model.addAttribute("title", String.format("CITIES FROM %s - %s", countryName, code).toUpperCase());

        return "cities";
    }
}
