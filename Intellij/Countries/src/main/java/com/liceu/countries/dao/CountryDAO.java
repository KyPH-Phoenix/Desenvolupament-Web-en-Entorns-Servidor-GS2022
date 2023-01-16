package com.liceu.countries.dao;

import com.liceu.countries.model.Country;

import java.util.List;

public interface CountryDAO {
    List<Country> getAll();
    List<Country> getAllByLanguage(String language);
    Country get(String countryCode);
    List<String> getDistricts(String countryCode);
    void deleteByLanguage(String language);
}
