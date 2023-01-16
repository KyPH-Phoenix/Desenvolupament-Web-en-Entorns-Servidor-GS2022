package com.liceu.countries.dao;

import com.liceu.countries.model.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityDAOMysql implements CityDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<City> getFromCountry(String countryCode) {
        return jdbcTemplate.query("SELECT Name, District, Population FROM city WHERE CountryCode = (?)", cityMapper, countryCode);
    }

    @Override
    public void addToCountry(String countryCode, City city) {
        jdbcTemplate.update("INSERT INTO city (Name, CountryCode, District, Population) " +
                "VALUES ((?), (?), (?), (?))", city.getName(), countryCode, city.getDistrict(),
                city.getPopulation());
    }

    private final RowMapper<City> cityMapper = (rs, rn) -> {
        City city = new City();

        city.setName(rs.getString("Name"));
        city.setDistrict(rs.getString("District"));
        city.setPopulation(rs.getInt("Population"));

        return city;
    };
}
