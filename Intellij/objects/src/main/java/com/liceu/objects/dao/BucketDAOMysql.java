package com.liceu.objects.dao;

import com.liceu.objects.model.Bucket;
import com.liceu.objects.model.BucketFile;
import com.liceu.objects.model.BucketObject;
import com.liceu.objects.model.ObjectVersion;
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
    public void createObject(BucketObject object) {
        jdbcTemplate.update("INSERT INTO object (username, bucketname, objectname) " +
                        "VALUES ((?), (?), (?))",
                object.getUsername(), object.getBucketname(), object.getObjectname());
    }

    @Override
    public int getObjectId(String objectname, String bucketname) {
        List<BucketObject> generated = jdbcTemplate.query("SELECT * FROM object " +
                        "WHERE objectname = (?) AND bucketname = (?)",
                new BeanPropertyRowMapper<>(BucketObject.class), objectname,
                bucketname);

        BucketObject resultObject = generated.stream().findFirst().orElse(null);

        return (resultObject == null) ? -1 : resultObject.getId();
    }

    @Override
    public void createFile(BucketFile bucketFile) {
        jdbcTemplate.update("INSERT INTO file (content, hash) VALUES ((?), (?))",
                bucketFile.getContent(), bucketFile.getHash());
    }

    @Override
    public int getFileId(String hash) {
        List<BucketFile> generated = jdbcTemplate.query("SELECT * FROM file " +
                        "WHERE hash = (?)", new BeanPropertyRowMapper<>(BucketFile.class),
                hash);

        BucketFile resultBucketFile = generated.stream().findAny().orElse(null);

        return (resultBucketFile == null) ? -1 : resultBucketFile.getId();
    }

    @Override
    public void createVersion(int idObject, int idFile) {
        jdbcTemplate.update("INSERT INTO version (idobject, idfile) VALUES ((?), (?));",
                idObject, idFile);
    }

    @Override
    public List<BucketFile> getAllFiles() {
        return jdbcTemplate.query("SELECT * FROM file",
                new BeanPropertyRowMapper<>(BucketFile.class));
    }

    @Override
    public ObjectVersion getLatestVersion(int idObject) {
        return jdbcTemplate.query("SELECT * FROM version WHERE idobject = (?) " +
                                "ORDER BY date DESC LIMIT 1",
                new BeanPropertyRowMapper<>(ObjectVersion.class), idObject)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<BucketObject> getAllObjectsFromDirectory(String directoryName, String bucketname) {
        return jdbcTemplate.query("SELECT * FROM object WHERE bucketname = (?) " +
                        "AND objectname LIKE '" + directoryName + "%'",
                new BeanPropertyRowMapper<>(BucketObject.class), bucketname);
    }

    @Override
    public List<ObjectVersion> getAllVersions(int objectId) {
        return jdbcTemplate.query("SELECT * FROM version WHERE idobject = (?) ORDER BY date DESC",
                new BeanPropertyRowMapper<>(ObjectVersion.class), objectId);
    }

    @Override
    public BucketFile getFile(int idfile) {
        return jdbcTemplate.query("SELECT * FROM file WHERE id = (?)",
                new BeanPropertyRowMapper<>(BucketFile.class), idfile)
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public BucketObject getObject(int objectid) {
        return jdbcTemplate.query("SELECT * FROM object WHERE id = (?)",
                new BeanPropertyRowMapper<>(BucketObject.class),objectid)
                .stream()
                .findFirst()
                .orElse(null);
    }
}