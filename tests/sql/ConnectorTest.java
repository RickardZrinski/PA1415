package tests.sql;

import org.junit.BeforeClass;
import org.junit.Test;

import sql.Connector;
import java.sql.Connection;

import static org.junit.Assert.assertTrue;

/**
 * @author  Dino Opijac
 * @since   12/05/14
 */
public class ConnectorTest {
    private static Connection connection;

    @BeforeClass
    public static void connect() {
        ConnectorTest.connection = Connector.getConnection();
    }

    @Test
    public void testGetConnection() throws Exception {
        assertTrue("A connection could not be established to the MySQL database", ConnectorTest.connection.isValid(50));
    }
}
