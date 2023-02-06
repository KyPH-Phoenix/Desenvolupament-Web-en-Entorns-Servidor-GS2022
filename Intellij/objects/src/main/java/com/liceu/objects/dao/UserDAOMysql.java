package com.liceu.objects.dao;

import com.liceu.objects.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOMysql implements UserDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void createUser(User user) {
        jdbcTemplate.update("INSERT INTO user (username, password, firstname, lastname, email) " +
                "VALUES ((?), (?), (?), (?), (?))", user.getUsername(), user.getPassword(),
                user.getFirstname(), user.getLastname(), user.getEmail());
    }

    @Override
    public List<User> getAll() {
        return jdbcTemplate.query("SELECT * FROM user",
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User getByUsername(String username, String password) {
        return jdbcTemplate.query("SELECT * FROM user WHERE username = (?) AND password = (?)",
                new BeanPropertyRowMapper<>(User.class), username, password)
                .stream()
                .findAny()
                .orElse(null);
    }


}
