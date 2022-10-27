package example.DAO;

import example.model.Message;

import java.util.List;

public interface MessageDao {
    void save(Message m);
    List<Message> getAllMessages();
}
