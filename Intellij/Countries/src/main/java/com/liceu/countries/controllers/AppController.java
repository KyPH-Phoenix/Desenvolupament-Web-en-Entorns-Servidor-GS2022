package com.liceu.countries.controllers;

import com.liceu.countries.model.City;
import com.liceu.countries.model.Country;
import com.liceu.countries.model.Language;
import com.liceu.countries.services.MyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        model.addAttribute("language", language);

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
    public String newCityPost(@PathVariable String code, HttpServletRequest req) {
        String cityName = req.getParameter("cityName");
        String district = req.getParameter("district");
        int population = Integer.parseInt(req.getParameter("population"));

        myService.addCityToCountry(code, cityName, district, population);

        return "City added succesfully<br><a href=\"/cities/" + code + "\">Go back</a>";
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

    @GetMapping("/deleteCountries/{language}")
    @ResponseBody
    public String deleteCountries(@PathVariable String language) {
        myService.deleteCuntriesByLanguage(language);

        return "Countries deleted succesfully<br><a href=\"/countries\">Go back</a>";
    }
}
