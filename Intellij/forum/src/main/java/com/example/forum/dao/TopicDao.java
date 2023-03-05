package com.example.forum.dao;

import com.example.forum.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicDao extends JpaRepository<Topic, Long> {
    List<Topic> findByIdLike(long id);
}
