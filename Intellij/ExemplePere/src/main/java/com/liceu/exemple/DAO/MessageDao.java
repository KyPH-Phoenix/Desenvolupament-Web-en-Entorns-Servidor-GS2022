package com.liceu.exemple.DAO;

import com.liceu.exemple.model.Message;

import java.util.List;

public interface MessageDao {

    Message save(Message message);
    List<Message> getAllMessages();
}