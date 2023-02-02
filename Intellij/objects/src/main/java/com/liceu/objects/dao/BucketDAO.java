package com.liceu.objects.dao;

import com.liceu.objects.model.Bucket;
import com.liceu.objects.model.BucketFile;
import com.liceu.objects.model.BucketObject;

import java.util.List;

public interface BucketDAO {
    List<Bucket> getAllFromUser(String username);
    void createBucket(String bucketname, String username);
    List<BucketObject> getAllObjectsFromBucket(String bucketname);
    int createObject(BucketObject object);
    int createFile(BucketFile bucketFile);
    void createVersion(int idObject, int idFile);
}
