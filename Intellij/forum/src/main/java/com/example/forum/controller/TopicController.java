package com.example.forum.controller;

import com.example.forum.model.Category;
import com.example.forum.model.Reply;
import com.example.forum.model.Topic;
import com.example.forum.service.CategoryService;
import com.example.forum.service.ReplyService;
import com.example.forum.service.TokenService;
import com.example.forum.service.TopicService;
import com.example.forum.utilities.TopicRequester;
import com.example.forum.utilities.Util;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@RestController
public class TopicController {
    TopicService topicService;
    TokenService tokenService;
    ReplyService replyService;
    CategoryService categoryService;

    public TopicController(TopicService topicService, TokenService tokenService, ReplyService replyService, CategoryService categoryService) {
        this.topicService = topicService;
        this.tokenService = tokenService;
        this.replyService = replyService;
        this.categoryService = categoryService;
    }

    @GetMapping("/categories/{slug}/topics")
    @CrossOrigin(origins = "http://localhost:3000")
    public List<Object> getTopics(@PathVariable String slug) {
        List<Object> results = new ArrayList<>();

        Category category = categoryService.findBySlugLike(slug).get(0);

        List<Topic> topics = topicService.getAllTopicsFromCategory(category.getId());

        topics.forEach(topic -> {
            topicService.getTopicById(topic.getId());
            results.add(Util.buildTopicMap(topic, new ArrayList<>()));
        });

        return results;
    }

    @PostMapping("/topics")
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, Object> postTopic(@RequestBody TopicRequester topicRequester,
                                         @RequestHeader("Authorization") String auth) {

        String token = auth.replace("Bearer ", "");
        String content = topicRequester.getContent();
        String categorySlug = (topicRequester.getCategory());
        String title = topicRequester.getTitle();
        String userEmail = tokenService.getEmail(token);

        Topic topic = topicService.saveTopic(content, categorySlug, title, userEmail);

        return Util.buildTopicMap(topic, new ArrayList<>());
    }

    @GetMapping("/topics/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, Object> getTopic(@PathVariable long id) {
        Topic topic = topicService.getTopicById(id);
        List<Object> replies = new ArrayList<>();

        replyService.getAllRepliesFromTopic(topic.getId()).forEach(reply -> {
            replies.add(Util.buildReplyMap(reply));
        });

        return Util.buildTopicMap(topic, replies);
    }

    @PutMapping("/topics/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, Object> updateTopic(@PathVariable long id, @RequestBody TopicRequester topicRequester) {
        String title = topicRequester.getTitle();
        String content = topicRequester.getContent();
        String slug = topicRequester.getCategory();
        long catId = categoryService.findBySlugLike(slug).get(0).getId();

        topicService.updateTopic(title, content, catId, id);

        Topic topic = topicService.getTopicById(id);
        List<Object> replies = new ArrayList<>();

        replyService.getAllRepliesFromTopic(topic.getId()).forEach(reply -> {
            replies.add(Util.buildReplyMap(reply));
        });

        return Util.buildTopicMap(topic, replies);
    }

    @DeleteMapping("/topics/{id}")
    @CrossOrigin(origins = "http://localhost:3000")
    public boolean deleteTopic(@PathVariable long id) {
        Topic topic = topicService.getTopicById(id);

        List<Reply> replies = replyService.getAllRepliesFromTopic(topic.getId());

        replies.forEach(reply -> {
            replyService.deleteReply(reply.getId());
        });

        topicService.deleteTopic(topic.getId());

        return true;
    }
}
