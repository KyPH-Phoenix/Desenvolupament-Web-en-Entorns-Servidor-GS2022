package example.service;

import example.DAO.MessageDao;
import example.DAO.MessageDaoMysql;
import example.model.Message;

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