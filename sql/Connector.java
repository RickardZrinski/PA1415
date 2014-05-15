package sql;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;

public class Connector {
    private static String HOSTNAME = "opij.ac";
    private static Integer PORT = 3306;
    private static String DATABASE = "pa1415";
    private static String USERNAME = "dino";
    private static String PASSWORD = "AYSWM8NZvrNQPyqX";

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
