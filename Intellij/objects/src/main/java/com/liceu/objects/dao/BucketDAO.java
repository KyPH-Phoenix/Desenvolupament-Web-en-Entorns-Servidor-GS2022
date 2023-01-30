package com.liceu.objects.dao;

import com.liceu.objects.model.Bucket;
import com.liceu.objects.model.BucketObject;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BucketDAO {
    List<Bucket> getAllFromUser(String username);
    void createBucket(String bucketname, String username);
    List<BucketObject> getAllObjectsFromBucket(String bucketname);
}
