package example.DAO;

import example.model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoMysql implements MessageDao {

    static Connection connection;

    private Connection getConnection() {
        if (connection != null) return connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:mysql://mysql:3307/llibre",
                    "root",
                    "root"
            );
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Message save(Message message) {
        try {
            Connection con = getConnection();
            String sql = "INSERT INTO visita (texte) values (?)";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, message.getText());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                int id = rs.getInt(1);
                message.setId(id);
                return message;
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Message> getAllMessages() {
        Connection con = getConnection();
        List<Message> messageList = new ArrayList<>();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM visita");

            while(rs.next()) {
                int id = rs.getInt(1);
                String text = rs.getString(2);

                Message message = new Message();
                message.setId(id);
                message.setText(text);

                messageList.add(message);
            }

            return messageList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
