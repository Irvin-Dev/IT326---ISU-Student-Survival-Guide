/**
 * Temporary account object to test AccountHandler CRUD operations.
 */
public class Account {

    private int accountId;      // Primary key
    private String username;    // Unique username
    private String email;       // User email
    private String password;    // Hashed password (never store plain text)
    private String role;        // e.g., "student", "admin"

    /**
     * Full constructor used when reading from the database.
     */
    public Account(int accountId, String username, String email, String password, String role) {
        this.accountId = accountId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Constructor used when creating a new account (ID auto-generated).
     */
    public Account(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters
    public int getAccountId() {
        return accountId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}