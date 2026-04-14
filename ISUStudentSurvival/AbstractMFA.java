package ISUStudentSurvival;

public abstract class AbstractMFA {

    // keeps compatibility with old code that still calls this
    public boolean twoFactorAuthentication() {
        return isChallengeActive();
    }

    // starts an MFA challenge by generating and sending a one-time code.
    public abstract void startChallenge(Account account);

    // returns true only when the user enters the correct active code.
    public abstract boolean verifyCode(String inputCode);

    // checks whether the current MFA challenge is still valid.
    public abstract boolean isChallengeActive();
    
}
