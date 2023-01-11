package com.liceu.countries.dao;

import com.liceu.countries.model.Country;

import java.util.List;

public interface CountryDAO {
    List<Country> getAll();
    List<Country> getAllForLanguage(String language);
    Country get(String countryCode);
}
