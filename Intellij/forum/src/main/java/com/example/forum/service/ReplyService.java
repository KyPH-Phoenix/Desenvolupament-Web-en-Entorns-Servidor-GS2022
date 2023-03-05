package com.example.forum.service;

import com.example.forum.dao.ReplyDao;
import com.example.forum.model.Reply;
import com.example.forum.model.Topic;
import com.example.forum.model.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ReplyService {
    ReplyDao replyDao;

    public ReplyService(ReplyDao replyDao) {
        this.replyDao = replyDao;
    }

    public List<Reply> getAllRepliesFromTopic(long id) {
        return replyDao.findAllReplyByTopicId(id);
    }

    public synchronized Reply createReply(String content, User user, Topic topic) {
        Reply reply = new Reply();

        reply.setContent(content);
        reply.setUser(user);
        reply.setTopic(topic);

        return replyDao.save(reply);
    }

    public synchronized void deleteReply(long id) {
        replyDao.deleteById(id);
    }

    public synchronized void updateReply(long replyId, String content) {
        replyDao.updateReply(content, Instant.now(), replyId);
    }

    public Reply getById(long replyId) {
        return replyDao.findByIdLike(replyId);
    }
}
