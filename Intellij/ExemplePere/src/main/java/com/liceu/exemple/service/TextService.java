package com.liceu.exemple.service;

import com.liceu.exemple.model.Message;
import com.liceu.exemple.DAO.MessageDao;
import com.liceu.exemple.DAO.MessageDaoMysql;

import java.util.List;

public class TextService {
    static MessageDao messageDao = new MessageDaoMysql();
    public List<Message> getALlMessages() {
        return messageDao.getAllMessages();
    }

    public void newText(String text) {
        Message message = new Message();
        message.setText(text);

        message = messageDao.save(message);
        message.getId();
    }
}