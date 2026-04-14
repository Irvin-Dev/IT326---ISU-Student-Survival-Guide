package ISUStudentSurvival;

import java.util.Random;

public class MFAImplementation extends AbstractMFA {

    private static final int CODE_MAX = 1_000_000;
    private static final int MAX_ATTEMPTS = 3;
    private static final long EXPIRY_MILLIS = 120_000;

    private final Random random = new Random();

    private String currentCode;
    private long expiresAtMillis;
    private int attemptsRemaining;

    @Override
    public void startChallenge(Account account) {
        this.currentCode = generateCode();
        this.expiresAtMillis = System.currentTimeMillis() + EXPIRY_MILLIS;
        this.attemptsRemaining = MAX_ATTEMPTS;

        System.out.println("MFA code sent to " + account.getEmail() + ": " + currentCode);
    }

    @Override
    public boolean verifyCode(String inputCode) {
        if (!isChallengeActive()) {
            clearChallenge();
            return false;
        }

        attemptsRemaining--;
        boolean success = currentCode.equals(inputCode);

        if (success || attemptsRemaining <= 0) {
            clearChallenge();
        }

        return success;
    }

    @Override
    public boolean isChallengeActive() {
        return currentCode != null
                && System.currentTimeMillis() < expiresAtMillis
                && attemptsRemaining > 0;
    }

    @Override
    public boolean twoFactorAuthentication() {
        return isChallengeActive();
    }

    private String generateCode() {
        return String.format("%06d", random.nextInt(CODE_MAX));
    }

    private void clearChallenge() {
        this.currentCode = null;
        this.expiresAtMillis = 0;
        this.attemptsRemaining = 0;
    }
}