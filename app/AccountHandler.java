package app;
import java.sql.*;

/**
 * Handles CRUD operations for the Account table.
 * Uses DBCRUDHandler for shared connection logic.
 */
public class AccountHandler extends DBCRUDHandler<Account> {


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
     * @throws SQLException If the SQL operation fails.
     */
    @Override
    public boolean create(Account account) throws SQLException {
        if(this.get(account.getAccountId()) != null) {
            System.out.println("Account with ID " + account.getAccountId() + " already exists.");
            return false;
        }
        
        String sql = "INSERT INTO account (ACCOUNTID, EMAIL, NAME, ANONYMITY, NOTIFICATIONS, DEPARTMENT, YEARVALUE, PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = open();

             PreparedStatement stmt = conn.prepareStatement(sql)) {
                System.out.println("Creating account: " + account);

            stmt.setInt(1, account.getAccountId());
            stmt.setString(2, account.getEmail());
            stmt.setString(3, account.getName());
            stmt.setBoolean(4, account.isAnonymity());
            stmt.setBoolean(5, account.isNotifications());
            stmt.setString(6, account.getDepartment());
            stmt.setInt(7, account.getYearValue());
            stmt.setString(8, account.getPassword());

            stmt.executeUpdate();
            return true;
        }
    }

    /**
     * Retrieves an Account by its ID.
     *
     * @param id Primary key of the account.
     * @return Matching Account, or null if not found.
     * @throws SQLException If the SQL operation fails.
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
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getInt("yearValue"),
                    rs.getBoolean("anonymity"),
                    rs.getBoolean("notifications"),
                    rs.getString("department")
                );
            }
        }
        return null;
    }

    /**
     * Updates an existing Account record.
     *
     * @param account Updated account data.
     * @return True if the update succeeds.
     * @throws SQLException If the SQL operation fails.
     */
    @Override
public boolean update(Account account) throws SQLException {
    String sql = "UPDATE account SET "
               + "EMAIL = ?, "
               + "NAME = ?, "
               + "ANONYMITY = ?, "
               + "NOTIFICATIONS = ?, "
               + "DEPARTMENT = ?, "
               + "YEARVALUE = ?, "
               + "PASSWORD = ? "
               + "WHERE ACCOUNTID = ?";

    try (Connection conn = open();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, account.getEmail());
        stmt.setString(2, account.getName());
        stmt.setBoolean(3, account.isAnonymity());
        stmt.setBoolean(4, account.isNotifications());
        stmt.setString(5, account.getDepartment());
        stmt.setInt(6, account.getYearValue());
        stmt.setString(7, account.getPassword());
        stmt.setInt(8, account.getAccountId()); // WHERE clause

        stmt.executeUpdate();
        return true;
    }
}


    /**
     * Deletes an Account by its ID.
     *
     * @param id Primary key of the account.
     * @return True if the delete succeeds.
     * @throws SQLException If the SQL operation fails.
     */
    @Override
    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM account WHERE accountId = ?";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
