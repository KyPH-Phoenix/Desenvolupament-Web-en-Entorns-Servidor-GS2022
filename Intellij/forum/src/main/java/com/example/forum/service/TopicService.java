package com.example.forum.service;

import com.example.forum.dao.TopicDao;
import com.example.forum.model.Topic;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class TopicService {
    TopicDao topicDao;
    CategoryService categoryService;
    UserService userService;

    public TopicService(TopicDao topicDao, CategoryService categoryService, UserService userService) {
        this.topicDao = topicDao;
        this.categoryService = categoryService;
        this.userService = userService;
    }

    public List<Topic> getAllTopicsFromCategory(Long id) {
        List<Topic> topics = topicDao.findAll();

        return topics.stream()
                .filter(topic -> topic.getCategory().getId().equals(id))
                .toList();
    }

    public Topic saveTopic(String content, String categorySlug, String title, String userEmail) {
        Topic topic = new Topic();
        topic.setContent(content);
        topic.setTitle(title);

        topic.setUser(userService.findByEmailLike(userEmail).get(0));
        topic.setCategory(categoryService.findBySlugLike(categorySlug).get(0));

        return topicDao.save(topic);
    }

    public Topic getTopicById(long id) {
        Topic topic = topicDao.findByIdLike(id).stream().findFirst().orElse(null);

        if (topic == null) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        return topic;
    }
}
