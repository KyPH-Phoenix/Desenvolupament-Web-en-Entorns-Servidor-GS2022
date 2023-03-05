package com.example.forum.dao;

import com.example.forum.model.Reply;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyDao extends JpaRepository<Reply, Long> {
    List<Reply> findAllReplyByTopicId(long id);
}
