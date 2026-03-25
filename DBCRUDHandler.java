import java.sql.Connection;
import java.sql.SQLException;

/**
 * Generic abstract CRUD handler that provides a consistent structure
 * for all database handlers in the system.
 *
 * @param <T> The model type this handler manages (e.g., Comment, Course, Account).
 */
public abstract class DBCRUDHandler<T> extends DBConnection {

    /**
     * Constructor passes database credentials to DBConnection.
     */
    protected DBCRUDHandler(String url, String user, String password) {
        super(url, user, password);
    }

    /**
     * Creates a new record in the database.
     * Subclasses implement the SQL INSERT logic for their specific table.
     *
     * @param obj The object to insert.
     * @throws SQLException If the database operation fails.
     */
    public abstract boolean create(T obj) throws SQLException;

    /**
     * Retrieves a single record by its primary key.
     * Subclasses implement the SQL SELECT logic and map the ResultSet to T.
     *
     * @param id The primary key of the record.
     * @return The object if found, otherwise null.
     * @throws SQLException If the database operation fails.
     */
    public abstract T get(int id) throws SQLException;


    /**
     * Updates an existing record in the database.
     * Subclasses implement the SQL UPDATE logic.
     *
     * @param obj The updated object.
     * @throws SQLException If the database operation fails.
     */
    public abstract boolean update(T obj) throws SQLException;

    /**
     * Deletes a record from the database by its primary key.
     * Subclasses implement the SQL DELETE logic.
     *
     * @param id The primary key of the record to delete.
     * @throws SQLException If the database operation fails.
     */
    public abstract boolean delete(int id) throws SQLException;

    /**
     * Convenience method for subclasses to open a new database connection.
     * This simply exposes the protected getConnection() from DBConnection.
     *
     * @return A new active database connection.
     * @throws SQLException If the connection cannot be opened.
     */
    protected Connection open() throws SQLException {
        return getConnection();
    }
}