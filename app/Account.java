package app;
/**
 * Temporary account object to test AccountHandler CRUD operations.
 */
public class Account {

    private int accountId;      // Primary key
    private String email;       // User email 
    private String name;
    private int yearValue;       // e.g., 1 for freshman, 2 for sophomore, etc. 
    private String password;
    private boolean anonymity;  // e.g., true for anonymous, false for visible
    private boolean notifications; // e.g., true for enabled, false for disabled
    private String department;  // User's department

    /**
     * Full constructor used when reading from the database.
     */
    public Account(int accountId, String name, String email, String password, int yearValue, boolean anonymity, boolean notifications, String department) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.yearValue = yearValue;
        this.anonymity = anonymity;
        this.notifications = notifications;
        this.department = department;
    }

    /**
     * Constructor used when creating a new account (ID auto-generated).
     */
    public Account(int accountId, String name, String email, String password, int yearValue, String department) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.yearValue = yearValue;
        this.anonymity = false;
        this.notifications = false;
        this.department = department;
    }

    // Getters
    public int getAccountId() {
        return accountId;
    }


    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public int getYearValue() {
        return yearValue;
    }
    public boolean isAnonymity() {
        return anonymity;
    }
    public boolean isNotifications() {
        return notifications;
    }
    public String getDepartment() {
        return department;
    }
    


    // Setters
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setYearValue(int yearValue) {
        this.yearValue = yearValue;
    }


    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", yearValue=" + yearValue +
                ", anonymity=" + anonymity +
                ", notifications=" + notifications +
                ", department='" + department + '\'' +
                '}';
    }
}