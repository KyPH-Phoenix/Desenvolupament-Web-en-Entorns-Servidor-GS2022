package com.liceu.ExempleSpring.DAO;

import com.liceu.ExempleSpring.model.Person;

import java.util.List;

public interface PersonDAO {
    void save(Person person);
    List<Person> getAll();
}
