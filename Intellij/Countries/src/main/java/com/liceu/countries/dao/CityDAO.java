package com.liceu.countries.dao;

import com.liceu.countries.model.City;

import java.util.List;

public interface CityDAO {
    List<City> getFromCountry(String countryCode);
    void addToCountry(String countryCode, City city);
}
