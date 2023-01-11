package com.liceu.countries.dao;

import com.liceu.countries.model.Language;

import java.util.List;

public interface LanguageDAO {
    List<Language> getAllFromCountry(String countryCode);
}
