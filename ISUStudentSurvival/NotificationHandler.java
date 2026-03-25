package ISUStudentSurvival;

public class NotificationHandler {
    private String message;

    public NotificationHandler(String message) {
        this.message = message;
    }

    public void notify(Account account) {
        if (account.getSettings().isToggleNotifications()) {
            System.out.println("Notification sent to " + account.getEmail() + ": " + message);
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
