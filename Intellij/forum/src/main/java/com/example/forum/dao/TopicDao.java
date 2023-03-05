package com.example.forum.dao;

import com.example.forum.model.Topic;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
public interface TopicDao extends JpaRepository<Topic, Long> {
    List<Topic> findByIdLike(long id);

    @Modifying
    @Query("update Topic t set t.title = :title, t.content = :content, t.category.id = :categoryid where t.id = :topicid")
    void updateTopic(@Param("title") String title, @Param("content")String content, @Param("categoryid") long catId, @Param("topicid") long topicId);
}
