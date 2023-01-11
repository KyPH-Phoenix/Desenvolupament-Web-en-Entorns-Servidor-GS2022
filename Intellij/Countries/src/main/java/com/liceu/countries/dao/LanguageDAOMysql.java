package com.liceu.countries.dao;

import com.liceu.countries.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanguageDAOMysql implements LanguageDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Language> getAllFromCountry(String countryCode) {
        return jdbcTemplate.query("SELECT Language, IsOfficial, Percentage FROM countrylanguage WHERE " +
                        "CountryCode = (?) ORDER BY IsOfficial",
                languageMapper, countryCode);
    }

    private final RowMapper<Language> languageMapper = (rs, rn) -> {
        Language language = new Language();

        language.setName(rs.getString("Language"));
        language.setOficial(rs.getBoolean("IsOfficial"));
        language.setPercentage(rs.getInt("Percentage"));

        return language;
    };
}
