package com.liceu.demo.dao;

import com.liceu.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAOMysql implements PersonDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Person> all() {
        return jdbcTemplate.query("SELECT * FROM person",
                new BeanPropertyRowMapper<>(Person.class));
    }

    @Override
    public void newPerson(Person person) {
        jdbcTemplate.update("INSERT INTO person (name, age) VALUES ((?), (?));",
                person.getName(), person.getAge());
    }
}
