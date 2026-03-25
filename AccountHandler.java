import java.sql.*;


/**
 * Handles all CRUD operations for the Account table.
 * Extends DBCRUDHandler to inherit connection handling and method structure.
 */
public class AccountHandler extends DBCRUDHandler<Account> {

    /**
     * Passes database credentials to the parent DBCRUDHandler.
     */
    public AccountHandler(String url, String user, String password) {
        super(url, user, password);
    }

    /**
     * Inserts a new Account into the database.
     *
     * @param account The Account object to insert.
     * @throws SQLException If the INSERT fails.
     */
    @Override
    public boolean create(Account account) throws SQLException {
        String sql = "INSERT INTO account (username, email, password, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getEmail());
            stmt.setString(3, account.getPassword()); // Should already be hashed
            stmt.setString(4, account.getRole());

            stmt.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Retrieves an Account by its primary key.
     *
     * @param id The accountId to search for.
     * @return The Account object, or null if not found.
     * @throws SQLException If the SELECT fails.
     */
    @Override
    public Account get(int id) throws SQLException {
        String sql = "SELECT * FROM account WHERE accountId = ?";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Account(
                    rs.getInt("accountId"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getString("role")
                );
            }
        }
        return null;
    }


    /**
     * Updates an existing Account record.
     *
     * @param account The updated Account object.
     * @throws SQLException If the UPDATE fails.
     */
    @Override
    public boolean update(Account account) throws SQLException {
        String sql = "UPDATE account SET username = ?, email = ?, password = ?, role = ? "
                   + "WHERE accountId = ?";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, account.getUsername());
            stmt.setString(2, account.getEmail());
            stmt.setString(3, account.getPassword());
            stmt.setString(4, account.getRole());
            stmt.setInt(5, account.getAccountId());

            stmt.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Deletes an Account by its primary key.
     *
     * @param id The accountId to delete.
     * @throws SQLException If the DELETE fails.
     */
    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM account WHERE accountId = ?";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}