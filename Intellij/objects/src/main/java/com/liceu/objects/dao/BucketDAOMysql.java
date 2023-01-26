package com.liceu.objects.dao;

import com.liceu.objects.model.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BucketDAOMysql implements BucketDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Bucket> getAllFromUser(String username) {
        return jdbcTemplate.query("SELECT * FROM bucket WHERE username = (?)",
                new BeanPropertyRowMapper<>(Bucket.class), username);
    }
}
