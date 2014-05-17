package tests.utilities.sql;

import com.mysql.jdbc.exceptions.MySQLInvalidAuthorizationSpecException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import utilities.sql.Connector;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author  Dino Opijac
 * @since   12/05/14
 */
public class ConnectorTest {
    @Test
    public void testGetConnection() {
        try {
            assertTrue("A connection could not be established to the MySQL database", Connector.getInstance().isValid(50));
        } catch (SQLException e) {
            fail(e.getMessage());
        }
    }
}
