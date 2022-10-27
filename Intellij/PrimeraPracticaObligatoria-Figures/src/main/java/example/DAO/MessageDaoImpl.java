package example.DAO;

import example.model.Message;

import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl implements MessageDao {
    static List<Message> messageList = new ArrayList<>();
    static int currentId = 1;

    @Override
    public synchronized void save(Message m) {
        m.setId(currentId);
        messageList.add(m);
        currentId++;
    }

    @Override
    public List<Message> getAllMessages() {
        return messageList;
    }
}
