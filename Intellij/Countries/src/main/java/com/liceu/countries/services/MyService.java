package com.liceu.countries.services;

import com.liceu.countries.dao.CityDAO;
import com.liceu.countries.dao.CountryDAO;
import com.liceu.countries.dao.LanguageDAO;
import com.liceu.countries.model.City;
import com.liceu.countries.model.Country;

import com.liceu.countries.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {
    @Autowired
    CountryDAO countryDAO;

    @Autowired
    CityDAO cityDAO;

    @Autowired
    LanguageDAO languageDAO;

    public List<Country> getAllCountries() {
        return countryDAO.getAll();
    }

    public List<Country> getAllCountriesForLanguage(String language) { return countryDAO.getAllForLanguage(language); }

    public List<City> getCitiesFromCountry(String code) {
        return cityDAO.getFromCountry(code);
    }

    public Country getCountry(String code) { return countryDAO.get(code); }

    public List<Language> getLanguagesFromCountry(String code) { return languageDAO.getAllFromCountry(code); }

}
