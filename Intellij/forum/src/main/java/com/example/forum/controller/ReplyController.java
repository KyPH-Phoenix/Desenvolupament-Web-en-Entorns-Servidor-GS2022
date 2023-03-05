package com.example.forum.controller;

import com.example.forum.model.Reply;
import com.example.forum.model.Topic;
import com.example.forum.model.User;
import com.example.forum.service.ReplyService;
import com.example.forum.service.TokenService;
import com.example.forum.service.TopicService;
import com.example.forum.service.UserService;
import com.example.forum.utilities.Util;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ReplyController {
    TokenService tokenService;
    UserService userService;
    TopicService topicService;
    ReplyService replyService;

    public ReplyController(TokenService tokenService, UserService userService, TopicService topicService, ReplyService replyService) {
        this.tokenService = tokenService;
        this.userService = userService;
        this.topicService = topicService;
        this.replyService = replyService;
    }

    @PostMapping("/topics/{topicId}/replies")
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, Object> postReply(@PathVariable long topicId, @RequestBody Reply replyContent,
                                         @RequestHeader("Authorization") String auth) {
        String content = replyContent.getContent();
        String token = auth.replace("Bearer ", "");
        String userEmail = tokenService.getEmail(token);

        User user = userService.findByEmailLike(userEmail).get(0);
        Topic topic = topicService.getTopicById(topicId);

        Reply reply = replyService.createReply(content, user, topic);

        return Util.buildReplyMap(reply);
    }

    @PutMapping("/topics/{topicId}/replies/{replyId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public Map<String, Object> updateReply(@PathVariable long topicId, @PathVariable long replyId,
                                           @RequestBody Reply replyContent, @RequestHeader("Authorization") String auth) {
        String content = replyContent.getContent();
        String token = auth.replace("Bearer ", "");
        String userEmail = tokenService.getEmail(token);

        User user = userService.findByEmailLike(userEmail).get(0);
        Topic topic = topicService.getTopicById(topicId);

        replyService.updateReply(replyId, content);

        Reply reply = replyService.getById(replyId);

        return Util.buildReplyMap(reply);
    }

    @DeleteMapping("/topics/{topicId}/replies/{replyId}")
    @CrossOrigin(origins = "http://localhost:3000")
    public boolean deleteReply(@PathVariable long topicId, @PathVariable long replyId) {
        replyService.deleteReply(replyId);

        return true;
    }
}

