package com.liceu.demo.service;

import com.liceu.demo.dao.PersonDAO;
import com.liceu.demo.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {
    @Autowired
    PersonDAO personDAO;

    public List<Person> getAllPeople() {
        return personDAO.all();
    }

    public void newPerson(String name, int age) {
        Person person = new Person(name, age);

        personDAO.newPerson(person);
    }
}
