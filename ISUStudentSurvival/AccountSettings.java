package ISUStudentSurvival;

public class AccountSettings {
    private boolean toggleAnonymity;
    private boolean toggleNotifications;

    public AccountSettings() {
        this.toggleAnonymity = false;
        this.toggleNotifications = false;
    }

    public void toggleAnonymity() {
        this.toggleAnonymity = !this.toggleAnonymity;
    }

    public void toggleNotifications() {
        this.toggleNotifications = !this.toggleNotifications;
    }


    public boolean isToggleNotifications() {
        return this.toggleNotifications;
    }

    public boolean isToggleAnonymity() {
        return this.toggleAnonymity;
    }
}