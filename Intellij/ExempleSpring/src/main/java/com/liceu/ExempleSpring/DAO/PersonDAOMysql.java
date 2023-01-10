package com.liceu.ExempleSpring.DAO;

import com.liceu.ExempleSpring.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDAOMysql implements PersonDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person (name) VALUES (?)", person.getName());
    }

    @Override
    public List<Person> getAll() {
        return jdbcTemplate.query("SELECT * FROM Person", personMapper);
    }

    private final RowMapper<Person> personMapper = (rs, rn) -> {
        Person person = new Person();
        person.setName(rs.getString("name"));
        person.setId(rs.getInt("id"));
        return person;
    };
}
