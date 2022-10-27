package example.service;

import example.DAO.MessageDao;
import example.DAO.MessageDaoImpl;
import example.model.Message;

import java.util.Date;
import java.util.List;

public class TextService {
    MessageDao messageDAO = new MessageDaoImpl();

    public void newText(String text) {
        Message message = new Message(text, new Date());
        messageDAO.save(message);
    }

    public List<Message> getALlMessages() {
        return messageDAO.getAllMessages();
    }
}
