package ISUStudentSurvival;

import java.util.Objects;
import java.util.Random;

public class Account {
    private String email;
    private String password;
    private int accountID;
    private String name;
    private String year;
    private AccountSettings settings;
    private String username;

    private static final Random random = new Random();

    public Account(String email, String password, String name, String year, String username) {
        this.email = email;
        this.password = password;
        this.accountID = generateID();
        this.name = name;
        this.year = year;
        this.settings = new AccountSettings();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public AccountSettings getSettings() {
        return settings;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    private int generateID() {
        int min = 100000000;
        int max = 900000000;
        return random.nextInt((max - min) + 1) + min;
    }

    public void changeUsername(String newUsername) {
        String oldUsername = this.username;
        this.username = newUsername;
    }

    public void changePassword(String password, String newPassword) {
        if (Objects.equals(password, this.password)) {
            this.password = newPassword;
        }
    }
}