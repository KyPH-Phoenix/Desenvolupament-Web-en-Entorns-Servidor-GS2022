package com.liceu.maze.DAO;

import com.liceu.maze.model.Winner;

import java.sql.*;
import java.util.List;

public class WinnerDaoMysql implements WinnerDao {
    static Connection connection;
    private Connection getConnection() {
        if (connection != null) return connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:mysql://mysql:3306/mazeWinners",
                    "root",
                    "root"
            );
            return connection;
        } catch (Exception e    ) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addWinner(Winner winner) {
        try {
            Connection con = getConnection();
            String insert = "INSERT INTO `winner` (`userName`, `mapName`, `elapsedTime`) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, winner.getUserName());
            ps.setString(2, winner.getMapName());
            ps.setLong(3, winner.getElapsedTime());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                System.out.println("Ha anat be");
                System.out.println(rs.getInt(1));
            } else {
                System.out.println("No ha anat be");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Winner> getWinners() {
        return null;
    }
}
