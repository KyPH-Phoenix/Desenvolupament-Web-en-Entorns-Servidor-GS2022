package com.liceu.objects.service;

import com.liceu.objects.dao.BucketDAO;
import com.liceu.objects.model.*;
import com.liceu.objects.util.Utilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BucketService {
    @Autowired
    BucketDAO bucketDAO;

    public List<Bucket> getBucketsFromUser(User user) {
        String username = user.getUsername();

        return bucketDAO.getAllFromUser(username);
    }

    public void createBucket(String bucketname, String username) {
        bucketDAO.createBucket(bucketname, username);
    }

    public List<BucketObject> getAllObjectsFromBucket(String bucketname) {
        return bucketDAO.getAllObjectsFromBucket(bucketname);
    }

    public void createObject(MultipartFile file, String path, String bucketname, User user) throws IOException {
        byte[] content = file.getBytes();
        String hash = Utilities.getSHA512(content);
        path += lastCharSlash(path) ? "" : "/";
        path = (firstCharSlash(path) ? "" : "/") + path;
        path = path.replaceAll("/+", "/");

        String objectname = path + (file.getOriginalFilename());

        if (fileDosntExists(hash)) {
            BucketFile bucketFile = new BucketFile();
            bucketFile.setContent(content);
            bucketFile.setHash(hash);

            bucketDAO.createFile(bucketFile);
        }

        int idFile = bucketDAO.getFileId(hash);
        int idObject;

        if (objectDosntExists(objectname, bucketname)) {
            BucketObject object = new BucketObject();
            object.setObjectname(objectname);
            object.setBucketname(bucketname);
            object.setUsername(user.getUsername());

            bucketDAO.createObject(object);
            idObject = bucketDAO.getObjectId(objectname, bucketname);

            bucketDAO.createVersion(idObject, idFile);
        } else {
            idObject = bucketDAO.getObjectId(objectname, bucketname);

            boolean versionRepeated = checkLastVersion(idObject, idFile);

            if (!versionRepeated) {
                bucketDAO.createVersion(idObject, idFile);
            }
        }
    }

    private boolean checkLastVersion(int idObject, int idFile) {
        ObjectVersion version = bucketDAO.getLatestVersion(idObject);

        return (version.getIdfile() == idFile);
    }

    private boolean fileDosntExists(String hash) {
        List<BucketFile> files = bucketDAO.getAllFiles();

        return files.stream()
                .noneMatch(file -> file.getHash().equals(hash));
    }

    private boolean objectDosntExists(String objectname, String bucketname) {
        List<BucketObject> objects = bucketDAO.getAllObjectsFromBucket(bucketname);

        return objects.stream()
                .noneMatch(object -> object.getObjectname().equals(objectname));
    }


    private boolean lastCharSlash(String path) {
        return path.charAt(path.length() - 1) == '/';
    }
    private boolean firstCharSlash(String path) {
        return path.charAt(0) == '/';
    }

    public List<BucketObject> getAllObjectsFromDirectory(String directoryName, String bucketname) {
        return bucketDAO.getAllObjectsFromDirectory(directoryName, bucketname);
    }

    public List<ObjectVersion> getAllVersionsFromObject(String objectName, String bucketname) {
        int objectId = bucketDAO.getObjectId(objectName, bucketname);

        return bucketDAO.getAllVersions(objectId);
    }

    public BucketFile getFile(int idfile) {
        return bucketDAO.getFile(idfile);
    }

    public BucketObject getObject(int objectid) {
        return bucketDAO.getObject(objectid);
    }
}
