package com.liceu.objects.dao;

import com.liceu.objects.model.Bucket;
import com.liceu.objects.model.BucketFile;
import com.liceu.objects.model.BucketObject;
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

    @Override
    public void createBucket(String bucketname, String username) {
        jdbcTemplate.update("INSERT INTO bucket (username, bucketname) VALUES ((?), (?))",
                username, bucketname);
    }

    @Override
    public List<BucketObject> getAllObjectsFromBucket(String bucketname) {
        return jdbcTemplate.query("SELECT * FROM object WHERE bucketname = (?)",
                new BeanPropertyRowMapper<>(BucketObject.class), bucketname);
    }

    @Override
    public int createObject(BucketObject object) {
        System.out.println(object.getObjectname());
        jdbcTemplate.update("INSERT INTO object (username, bucketname, objectname) " +
                        "VALUES ((?), (?), (?))",
                object.getUsername(), object.getBucketname(), object.getObjectname());

        List<BucketObject> generated = jdbcTemplate.query("SELECT * FROM object " +
                "WHERE objectname = (?) AND bucketname = (?)",
                new BeanPropertyRowMapper<>(BucketObject.class), object.getObjectname(),
                object.getBucketname());

        BucketObject resultObject = generated.stream().findFirst().orElse(null);

        return (resultObject == null) ? -1 : resultObject.getId();
    }

    @Override
    public int createFile(BucketFile bucketFile) {
        jdbcTemplate.update("INSERT INTO file (content, hash) VALUES ((?), (?))",
                bucketFile.getContent(), bucketFile.getHash());

        List<BucketFile> generated = jdbcTemplate.query("SELECT * FROM file " +
                "WHERE hash = (?)", new BeanPropertyRowMapper<>(BucketFile.class),
                bucketFile.getHash());

        BucketFile resultBucketFile = generated.stream().findAny().orElse(null);

        return (resultBucketFile == null) ? -1 : resultBucketFile.getId();
    }

    @Override
    public void createVersion(int idObject, int idFile) {
        System.out.println(idObject);
        System.out.println(idFile);

        jdbcTemplate.update("INSERT INTO version (idobject, idfile) VALUES ((?), (?));",
                idObject, idFile);
    }
}