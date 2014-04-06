package sql;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connector {
    private static String HOSTNAME = "****";
    private static Integer PORT = 3306;
    private static String DATABASE = "****";
    private static String USERNAME = "****";
    private static String PASSWORD = "****";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(String.format("jdbc:mysql://%s:%s/%s", HOSTNAME, PORT, DATABASE), USERNAME, PASSWORD);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
}
