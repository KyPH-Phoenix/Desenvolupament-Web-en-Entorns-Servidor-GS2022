package designpatterns.singleton;

public class Main {
    public static void main(String[] args) {
        Singleton s = Singleton.getInstance();
        s.getDBConnection();
    }
}

class Singleton {
    private String DBConnection;
    private static Singleton instance;

    private Singleton() {
    }

    static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public String getDBConnection() {
        return DBConnection;
    }

    public void setDBConnection(String DBConnection) {
        this.DBConnection = DBConnection;
    }
}
