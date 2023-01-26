package com.liceu.objects.dao;

import com.liceu.objects.model.Bucket;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BucketDAO {
    List<Bucket> getAllFromUser(String username);
}
