import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.check.util.DBReader;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DBReaderTest {

    private DBReader dbReader;

    @BeforeEach
    void setUp() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/check";
        String user = "postgres";
        String password = "postgres";
        dbReader = new DBReader(url, user, password);
    }

    @AfterEach
    void tearDown() throws SQLException {
        dbReader.close();
    }

    @Test
    void testConnection() throws SQLException {
        Connection connection = dbReader.getConnection();
        assertNotNull(connection);
        assertFalse(connection.isClosed());
    }
}

