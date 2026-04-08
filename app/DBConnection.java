package app;
import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnection {

    /**
     * Opens a new database connection.
     */
    public Connection getConnection() throws SQLException;

    /**
     * Utility method to safely close a connection.
     */
    default void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ignored) {}
        }
    }
}
