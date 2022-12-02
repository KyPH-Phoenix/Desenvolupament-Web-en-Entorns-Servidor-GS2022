package com.liceu.exemple.DAO;

import com.liceu.exemple.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl implements MessageDao {
    static List<Message> messages = new ArrayList<>();
    static int lastId = 1;

    @Override
    public Message save(Message message) {
        message.setId(lastId);

        messages.add(message);

        return message;
    }

    @Override
    public List<Message> getAllMessages() {
        return messages;
    }
}