package com.liceu.ExempleSpring.service;

import com.liceu.ExempleSpring.DAO.PersonDAO;
import com.liceu.ExempleSpring.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyService {
    @Autowired
    PersonDAO personDAO;

    public int addNumbers(int num1, int num2) {
        return num1 + num2;
    }

    public void newPerson(String name) {
        Person person = new Person();
        person.setName(name);

        personDAO.save(person);
    }

    public List<Person> getAllPersons() {
        return personDAO.getAll();
    }
}
