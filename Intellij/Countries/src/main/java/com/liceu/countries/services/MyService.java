package com.liceu.countries.services;

import com.liceu.countries.dao.CityDAO;
import com.liceu.countries.dao.CountryDAO;
import com.liceu.countries.model.City;
import com.liceu.countries.model.Country;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {
    @Autowired
    CountryDAO countryDAO;

    @Autowired
    CityDAO cityDAO;

    public List<Country> getAllCountries() {
        return countryDAO.getAll();
    }

    public List<City> getCitiesFromCountry(String code) {
        return cityDAO.getFromCountry(code);
    }

    public Country getCountry(String code) {
        return countryDAO.get(code);
    }
}
