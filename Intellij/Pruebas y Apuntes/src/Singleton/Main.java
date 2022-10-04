package Singleton;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(List.of("A", "B", "C", "A", "A"));

        while (list.remove("A"));
    }
}

class Singleton {
    private String DBCconnection;
    private static Singleton instance;
    private Singleton() {}

    static Singleton getInstance() {
        if (instance == null) return new Singleton();
        return instance;
    }
}

class State {
    private static String DBConnection;

    public static String getDBConnection() {
        return DBConnection;
    }

    public static void setDBConnection(String DBConnection) {
        State.DBConnection = DBConnection;
    }
}
