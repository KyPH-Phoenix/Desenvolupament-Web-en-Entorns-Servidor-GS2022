package com.liceu.countries.dao;

import com.liceu.countries.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CountryDAOMysql implements CountryDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Country> getAll() {
        return jdbcTemplate.query("SELECT Code, Name, Population FROM country", countryMapper);
    }

    @Override
    public List<Country> getAllByLanguage(String language) {
        return jdbcTemplate.query("SELECT Code, Name, Population FROM country " +
                "JOIN countrylanguage ON Code = CountryCode WHERE Language = (?)",
                countryMapper, language);
    }

    @Override
    public Country get(String countryCode) {
        return jdbcTemplate.query("SELECT Code, Name, Population FROM country WHERE Code = (?)",
                countryMapper, countryCode).get(0);
    }

    @Override
    public List<String> getDistricts(String countryCode) {
        return jdbcTemplate.query("SELECT DISTINCT District FROM city WHERE CountryCode = (?) AND " +
                        "District != '' AND District != '–'",
                (rs, rn) -> rs.getString("District"),
                countryCode);
    }

    @Override
    public void deleteByLanguage(String language) {
        List<Country> countries = getAllByLanguage(language);
        List<String> countryCodes = new ArrayList<>();

        countries.forEach(country -> countryCodes.add(country.getCode()));
        countryCodes.forEach(code -> {
            jdbcTemplate.update("DELETE FROM countrylanguage WHERE CountryCode = (?)", code);
            jdbcTemplate.update("DELETE FROM city WHERE CountryCode = (?)", code);
            jdbcTemplate.update("DELETE FROM country WHERE Code = (?)", code);
        });
    }

    private final RowMapper<Country> countryMapper = (rs, rn) -> {
        Country country = new Country();
        country.setCode(rs.getString("Code"));
        country.setName(rs.getString("Name"));
        country.setPopulation(rs.getInt("Population"));

        return country;
    };
}
