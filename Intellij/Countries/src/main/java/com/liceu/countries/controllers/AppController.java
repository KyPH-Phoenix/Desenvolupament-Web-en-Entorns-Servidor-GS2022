package com.liceu.countries.controllers;

import com.liceu.countries.model.Country;
import com.liceu.countries.services.CountryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AppController {
    @Autowired
    CountryService countryService;

    @RequestMapping("/countries")
    public String countriesGet(Model model) {
        List<Country> countries = countryService.getAllCountries();

        return "countries";
    }
}
