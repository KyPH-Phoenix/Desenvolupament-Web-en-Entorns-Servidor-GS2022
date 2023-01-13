package com.liceu.countries.controllers;

import com.liceu.countries.model.City;
import com.liceu.countries.model.Country;
import com.liceu.countries.model.Language;
import com.liceu.countries.services.MyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.http.HttpResponse;
import java.util.List;

@Controller
public class AppController {
    @Autowired
    MyService myService;

    @GetMapping("/countries")
    public String allCountriesGet(Model model) {
        List<Country> countries = myService.getAllCountries();

        model.addAttribute("countries", countries);
        model.addAttribute("title", "ALL COUNTRIES");

        return "countries";
    }

    @GetMapping("/countries/{language}")
    public String allCountriesForLanguageGet(@PathVariable String language, Model model) {
        List<Country> countries = myService.getAllCountriesForLanguage(language);

        model.addAttribute("countries", countries);
        model.addAttribute("title", String.format("COUNTRIES WITH %s AS A LANGUAGE",
                language).toUpperCase());

        return "countries";
    }

    @GetMapping("/cities/{code}")
    public String citiesGet(@PathVariable String code, Model model) {
        List<City> cities = myService.getCitiesFromCountry(code);
        String countryName = myService.getCountry(code).getName();

        model.addAttribute("cities", cities);
        model.addAttribute("countryCode", code);
        model.addAttribute("title", String.format("CITIES FROM %s - %s",
                countryName, code).toUpperCase());

        return "cities";
    }

    @GetMapping("/newCity/{code}")
    public String newCityGet(@PathVariable String code, Model model) {
        String countryName = myService.getCountry(code).getName();
        List<String> districts = myService.getDistrictsFromCountry(code);

        model.addAttribute("countryCode", code);
        model.addAttribute("country", countryName.toUpperCase());
        model.addAttribute("districts", districts);

        return "newCityForm";
    }

    @PostMapping("/newCity/{code}")
    @ResponseBody
    public String newCityPost(@PathVariable String code, Model model) {
        String cityName = (String) model.getAttribute("cityName");
        String district = (String) model.getAttribute("district");
        String population = (String) model.getAttribute("population");

        System.out.println(cityName);
        System.out.println(district);
        System.out.println(population);

        return "City added succesfully";
    }

    @GetMapping("/languages/{code}")
    public String languagesGet(@PathVariable String code, Model model) {
        List<Language> languages = myService.getLanguagesFromCountry(code);
        String countryName = myService.getCountry(code).getName();

        model.addAttribute("languages", languages);
        model.addAttribute("title", String.format((countryName + " - " + code)
                .toUpperCase()));

        return "languages";
    }
}
