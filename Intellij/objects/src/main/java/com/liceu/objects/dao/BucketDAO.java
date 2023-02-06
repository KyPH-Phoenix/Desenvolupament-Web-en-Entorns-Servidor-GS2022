package com.liceu.objects.dao;

import com.liceu.objects.model.Bucket;
import com.liceu.objects.model.BucketFile;
import com.liceu.objects.model.BucketObject;
import com.liceu.objects.model.ObjectVersion;

import java.util.List;

public interface BucketDAO {
    List<Bucket> getAllFromUser(String username);
    void createBucket(String bucketname, String username);
    List<BucketObject> getAllObjectsFromBucket(String bucketname);
    void createObject(BucketObject object);
    int getObjectId(String objectname, String bucketname);
    void createFile(BucketFile bucketFile);
    int getFileId(String bucketFile);
    void createVersion(int idObject, int idFile);
    List<BucketFile> getAllFiles();
    ObjectVersion getLatestVersion(int idObject);
    List<BucketObject> getAllObjectsFromDirectory(String directoryName, String bucketname);
    List<ObjectVersion> getAllVersions(int objectId);
    BucketFile getFile(int idfile);
    BucketObject getObject(int objectid);
}
