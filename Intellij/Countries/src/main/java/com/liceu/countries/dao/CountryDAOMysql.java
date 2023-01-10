package com.liceu.countries.dao;

import com.liceu.countries.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
    public Country get(String countryCode) {
        return jdbcTemplate.query("SELECT Code, Name, Population FROM country WHERE Code = (?)",
                countryMapper, countryCode).get(0);
    }

    private final RowMapper<Country> countryMapper = (rs, rn) -> {
        Country country = new Country();
        country.setCode(rs.getString("Code"));
        country.setName(rs.getString("Name"));
        country.setPopulation(rs.getInt("Population"));

        return country;
    };

}
