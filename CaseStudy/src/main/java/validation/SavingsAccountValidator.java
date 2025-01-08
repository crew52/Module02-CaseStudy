package validation;

import entity.account.SavingsAccount;

public class SavingsAccountValidator {
    public static void validateTerm(SavingsAccount account) {
        if (account.getTerm() == null) {
            throw new IllegalStateException("Term not set for this savings account.");
        }
    }

    public static void validateCanWithdraw(SavingsAccount account) {
        if (!account.canWithdraw()) {
            throw new IllegalStateException("Cannot withdraw before term ends.");
        }
    }
}
