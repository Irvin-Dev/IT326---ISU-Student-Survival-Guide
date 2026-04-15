package app;
import java.util.List;

/**
 * Handles CRUD operations for the Account table.
 * Uses DBCRUDHandler for shared connection logic.
 */
public abstract class AccountHandler extends DBCRUDHandler<Account> {


    /**
     * Creates an AccountHandler using the given database connection.
     *
     * @param connection Database connection provider.
     */
    public AccountHandler(DBConnection connection) {
        super(connection);
    }

    /**
     * Inserts a new Account record.
     *
     * @param account Account to insert.
     * @return True if the insert succeeds.
     */
    @Override
    public abstract boolean create(Account account);

    /**
     * Retrieves an Account by its ID.
     *
     * @param id Primary key of the account.
     * @return Matching Account, or null if not found.
     */
    @Override
    public abstract Account get(int id);

    /**
     * Retrieves all Account records.
     *
     * @return List of all accounts, or null if none found.
     */
    public abstract List<Account> getAll();

    /**
     * Updates an existing Account record.
     *
     * @param account Updated account data.
     * @return True if the update succeeds.
     */
    @Override
public abstract boolean update(Account account);

    /**
     * Deletes an Account by its ID.
     *
     * @param id Primary key of the account.
     * @return True if the delete succeeds.
     */
    @Override
    public abstract boolean delete(int id);

}