package example.DAO;

import example.model.Message;

import java.util.List;

public interface MessageDao {

    Message save(Message message);
    List<Message> getAllMessages();
}