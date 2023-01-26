package com.liceu.objects.service;

import com.liceu.objects.dao.BucketDAO;
import com.liceu.objects.model.Bucket;
import com.liceu.objects.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BucketService {
    @Autowired
    BucketDAO bucketDAO;

    public List<Bucket> getBucketsFromUser(User user) {
        String username = user.getUsername();

        return bucketDAO.getAllFromUser(username);
    }
}
