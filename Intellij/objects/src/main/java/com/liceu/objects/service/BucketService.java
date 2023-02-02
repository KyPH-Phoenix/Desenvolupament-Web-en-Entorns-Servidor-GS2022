package com.liceu.objects.service;

import com.liceu.objects.dao.BucketDAO;
import com.liceu.objects.exception.ObjectAlreadyExistsException;
import com.liceu.objects.model.Bucket;
import com.liceu.objects.model.BucketFile;
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
        String objectname = path + ((lastCharSlash(path)) ? "" : "/") + file.getOriginalFilename();

//        if (objectAlreadyExists(objectname, bucketname)) {
//            throw new ObjectAlreadyExistsException();
//        } else {
//            bucketDAO.createObject(bucketname, content, hash, objectname, user.getUsername());
//        }

        BucketObject object = new BucketObject();
        object.setObjectname(objectname);
        object.setBucketname(bucketname);
        object.setUsername(user.getUsername());
        int idObject = bucketDAO.createObject(object);

        BucketFile bucketFile = new BucketFile();
        bucketFile.setContent(content);
        bucketFile.setHash(hash);
        int idFile = bucketDAO.createFile(bucketFile);

        bucketDAO.createVersion(idObject, idFile);
    }

    private boolean objectAlreadyExists(String objectname, String bucketname) {
        List<BucketObject> objects = bucketDAO.getAllObjectsFromBucket(bucketname);

        return objects.stream()
                .anyMatch(object -> object.getObjectname().equals(objectname));
    }


    private boolean lastCharSlash(String path) {
        return path.charAt(path.length() - 1) == '/';
    }
}
