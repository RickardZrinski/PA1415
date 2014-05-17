package utilities.sql;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static Connection instance = null;

    /**
     * Disallow instance of object
     */
    protected Connector() {}

    /**
     * Creates a singleton of the database connection
     * @return instance if it was created
     * @throws SQLException
     */
    public static Connection getInstance() throws SQLException {
        String hostname = "opij.ac";    // Do not change
        Integer port = 3306;            // Do not change

        String username = "";           // Your username
        String password = "";           // Your password

        String database = "pa1415";     // pa1415: standard database
                                        // username: your personal database


        if (username.isEmpty() || password.isEmpty())
            throw new SQLException("No username or password supplied to Connector");

        if (instance == null) {
            String url = String.format("jdbc:mysql://%s:%s/%s", hostname, port, database);
            instance = (Connection) DriverManager.getConnection(url, username, password);
        }

        return instance;
    }
}
