package ISUStudentSurvival;

import java.util.Scanner;

public class MFATest {

    public static void main(String[] args) {
        Account testAccount = new Account(
                "test@isu.edu",
                "password123",
                "Test User",
                "Sophomore",
                "tester"
        );

        MFAImplementation mfa = new MFAImplementation();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Starting MFA Test");

        mfa.startChallenge(testAccount);
        System.out.println("Challenge active after start: " + mfa.isChallengeActive());

        System.out.print("Enter the 6-digit code shown above: ");
        String userCode = scanner.nextLine();
        boolean verified = mfa.verifyCode(userCode);

        System.out.println("Verification result: " + verified);
        System.out.println("Challenge active after verify: " + mfa.isChallengeActive());

        System.out.println("\nNow testing failure after 3 bad attempts");
        mfa.startChallenge(testAccount);

        for (int i = 1; i <= 3; i++) {
            boolean ok = mfa.verifyCode("000000");
            System.out.println("Bad attempt " + i + " result: " + ok);
        }

        System.out.println("Challenge active after 3 bad attempts: " + mfa.isChallengeActive());
        System.out.println("=== End Test ===");
    }

    
}
