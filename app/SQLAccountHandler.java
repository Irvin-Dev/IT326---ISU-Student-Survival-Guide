package app;
import java.sql.*;
import java.util.List;

/**
 * Handles CRUD operations for the Account table.
 * Uses DBCRUDHandler for shared connection logic.
 */
public class SQLAccountHandler extends DBCRUDHandler<Account> {


    /**
     * Creates an AccountHandler using the given database connection.
     *
     * @param connection Database connection provider.
     */
    public SQLAccountHandler(DBConnection connection) {
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
    public boolean create(Account account)  {
        if(this.getById(account.getAccountId()) != null) {
            return false;
        }

        String sql = "INSERT INTO account ( EMAIL, NAME, ANONYMITY, NOTIFICATIONS, DEPARTMENT, YEARVALUE, PASSWORD) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = open();

             PreparedStatement stmt = conn.prepareStatement(sql)) {

   
            stmt.setString(1, account.getEmail());
            stmt.setString(2, account.getName());
            stmt.setBoolean(3, account.isAnonymity());
            stmt.setBoolean(4, account.isNotifications());
            stmt.setString(5, account.getDepartment());
            stmt.setInt(6, account.getYearValue());
            stmt.setString(7, account.getPassword());

            stmt.executeUpdate();
            return true;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
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
    public Account getById(int id) {
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
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * Retrieves all Account records.
     *
     * @return List of all accounts, or null if none found.
     * @throws SQLException If the SQL operation fails.
     */
    @Override
    public List<Account> getAll() {
        String sql = "SELECT * FROM account";
        List<Account> accounts = new java.util.ArrayList<>();

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                accounts.add(new Account(
                    rs.getInt("accountId"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getInt("yearValue"),
                    rs.getBoolean("anonymity"),
                    rs.getBoolean("notifications"),
                    rs.getString("department")
                ));
            }
            return accounts;
        }
        catch(SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Updates an existing Account record.
     *
     * @param account Updated account data.
     * @return True if the update succeeds.
     * @throws SQLException If the SQL operation fails.
     */
    @Override
public boolean update(Account account)  {
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
    catch(SQLException e) {
        e.printStackTrace();
        return false;
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
    public boolean delete(int id) {
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