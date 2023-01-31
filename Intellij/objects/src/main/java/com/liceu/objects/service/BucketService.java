package com.liceu.objects.service;

import com.liceu.objects.dao.BucketDAO;
import com.liceu.objects.model.Bucket;
import com.liceu.objects.model.BucketObject;
import com.liceu.objects.model.User;
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
        String objectname = path + ((lastCharSlash(path)) ? "/" : "") + file;

//        if (objectExists(objectname, bucketname)) {
//
//        } else {
//            bucketDAO.createObject(bucketname, content, hash, objectname, user.getUsername());
//        }
    }

    private boolean lastCharSlash(String path) {
        return path.charAt(path.length() - 1) == '/';
    }
}
