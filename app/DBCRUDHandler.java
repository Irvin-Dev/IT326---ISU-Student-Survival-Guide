package app;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Base class for CRUD handlers.
 * Provides a shared connection interface and abstract CRUD operations.
 *
 * @param <T> Model type handled by this class.
 */
public abstract class DBCRUDHandler<T> {

    private final DBConnection connection;

    /**
     * Creates a CRUD handler using the given database connection.
     *
     * @param connection The database connection provider.
     */
    protected DBCRUDHandler(DBConnection connection) {
        this.connection = connection;
    }

    /**
     * Opens a new database connection.
     *
     * @return Active SQL connection.
     * @throws SQLException If the connection cannot be opened.
     */
    protected Connection open() throws SQLException {
        return connection.getConnection();
    }

    /**
     * Inserts a new record.
     *
     * @param obj Object to insert.
     * @return True if successful.
     * @throws SQLException If the insert fails.
     */
    public abstract boolean create(T obj) throws SQLException;

    /**
     * Retrieves a record by ID.
     *
     * @param id Primary key.
     * @return The matching object, or null.
     * @throws SQLException If the query fails.
     */
    public abstract T get(int id) throws SQLException;
    /**
     * Retrieves a records for the type.
     *
     * @param id Primary key.
     * @return The objects, or null.
     * @throws SQLException If the query fails.
     */


    /**
     * Updates an existing record.
     *
     * @param obj Updated object.
     * @return True if successful.
     * @throws SQLException If the update fails.
     */
    public abstract boolean update(T obj) throws SQLException;

    /**
     * Deletes a record by ID.
     *
     * @param id Primary key.
     * @return True if successful.
     * @throws SQLException If the delete fails.
     */
    public abstract boolean delete(int id) throws SQLException;
}
