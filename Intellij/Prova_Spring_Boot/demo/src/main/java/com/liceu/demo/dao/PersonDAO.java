package com.liceu.demo.dao;

import com.liceu.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PersonDAO {
    List<Person> all();
    void newPerson(Person person);
}
