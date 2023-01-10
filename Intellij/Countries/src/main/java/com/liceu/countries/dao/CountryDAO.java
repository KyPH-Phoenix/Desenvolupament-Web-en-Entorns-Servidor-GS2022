package com.liceu.countries.dao;

import com.liceu.countries.model.Country;

import java.util.List;

public interface CountryDAO {
    List<Country> getAll();
    Country get(String countryCode);
}
